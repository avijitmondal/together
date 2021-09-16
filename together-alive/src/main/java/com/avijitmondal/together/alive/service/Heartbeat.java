package com.avijitmondal.together.alive.service;

import com.avijitmondal.together.alive.data.Constants;
import com.avijitmondal.together.alive.model.HeartbeatEvent;
import com.avijitmondal.together.alive.model.HeartbeatState;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Heartbeat implements Runnable, Observable<HeartbeatState, HeartbeatEvent> {

    private final DatagramSocket datagramSocket;
    private final int port;
    private final ExecutorService executor;
    private String data;
    private final String expectedPayload;
    private final Observer<HeartbeatState, HeartbeatEvent> observer;
    private Thread runnableWorkerThread;
    private boolean isDead = false;

    Heartbeat(int port, Observer observer, String expectedPayload) throws IOException {
        this.executor = Executors.newSingleThreadExecutor();
        this.datagramSocket = new DatagramSocket(port);
        this.port = port;
        this.observer = observer;
        this.expectedPayload = expectedPayload;
        this.runnableWorkerThread = null;
    }

    void kill() {
        executor.shutdownNow();
        isDead = true;
        runnableWorkerThread.interrupt();
    }

    public int getPort() {
        return port;
    }

    private byte[] receiveHeartbeat() throws IOException {
        byte[] payload = receiveData(datagramSocket);
        data = new String(payload);
        return payload;
    }

    @Override
    public void run() {
        Future<byte[]> asynchronousHeartbeat = executor.submit(this::receiveHeartbeat);
        boolean lossCommsAlreadyNotified = false;
        int timeElapsed = 0;
        HeartbeatEvent event;
        runnableWorkerThread = Thread.currentThread();
        while (!isDead && !executor.isShutdown()) {
            while (!asynchronousHeartbeat.isDone()) {
                try {
                    Thread.sleep(Constants.TIME_INTERVAL);
                } catch (InterruptedException exc) {
                    if (data.equals(expectedPayload)) {
                        event = new HeartbeatEvent(data, timeElapsed, new Date().getTime());
                        notify(HeartbeatState.HOST_OFFLINE, event);
                        asynchronousHeartbeat.cancel(true);
                        Thread.currentThread().interrupt();
                        isDead = true;
                    }
                }
                timeElapsed += Constants.TIME_INTERVAL;
                if (timeElapsed > Constants.TIME_LOSS_COMMUNICATION_FRAME * Constants.TIME_INTERVAL && !lossCommsAlreadyNotified)
                    lossCommsAlreadyNotified = notifyLostComm(timeElapsed);

                if (timeElapsed > Constants.TIME_LIFE_FRAMES * Constants.TIME_INTERVAL)
                    isDead = notifyDeath(timeElapsed, asynchronousHeartbeat);
            }
            if (!isDead && data.equals(expectedPayload)) {
                notifyHeartbeat(lossCommsAlreadyNotified, timeElapsed);
                lossCommsAlreadyNotified = false;
                isDead = false;
                timeElapsed = 0;
            }
            asynchronousHeartbeat = executor.submit(this::receiveHeartbeat);
        }
        asynchronousHeartbeat.cancel(true);
    }

    private boolean notifyLostComm(int timeElapsed) {
        HeartbeatEvent event = new HeartbeatEvent(expectedPayload, timeElapsed, new Date().getTime());
        notify(HeartbeatState.COMMUNICATION_LOST, event);
        return true;
    }

    private boolean notifyDeath(int timeElapsed, Future<byte[]> asynchronousHeartbeat) {
        HeartbeatEvent event = new HeartbeatEvent(expectedPayload, timeElapsed, new Date().getTime());
        asynchronousHeartbeat.cancel(true);
        notify(HeartbeatState.HOST_OFFLINE, event);
        return true;
    }

    private void notifyHeartbeat(boolean lossCommsAlreadyNotified, int timeElapsed) {
        HeartbeatEvent event = new HeartbeatEvent(data, timeElapsed, new Date().getTime());
        if (lossCommsAlreadyNotified)
            notify(HeartbeatState.HOST_ONLINE, event);
        else
            notify(HeartbeatState.HEARTBEAT_RECEIVED, event);
    }

    @Override
    public void notify(HeartbeatState heartbeatState, HeartbeatEvent event) {
        observer.update(heartbeatState, event);
    }

    private byte[] receiveData(DatagramSocket datagramSocket) throws IOException {
        byte[] receiveData = new byte[Constants.UDP_VALID_PACKET_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        datagramSocket.receive(receivePacket);
        receiveData = receivePacket.getData();
        byte[] receivedData = new byte[receivePacket.getLength()];
        System.arraycopy(receiveData, receivePacket.getOffset(), receivedData, 0, receivePacket.getLength());
        return receivedData;
    }
}

