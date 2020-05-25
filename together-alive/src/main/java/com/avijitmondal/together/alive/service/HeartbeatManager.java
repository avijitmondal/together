package com.avijitmondal.together.alive.service;

import com.avijitmondal.together.alive.model.HeartbeatEvent;
import com.avijitmondal.together.alive.model.HeartbeatState;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HeartbeatManager implements Observer<HeartbeatState, HeartbeatEvent> {
    private static final Logger LOGGER = Logger.getLogger(HeartbeatManager.class.getName());

    private final HeartbeatListener listener;
    private final ExecutorService executor;
    private final Map<String, Heartbeat> monitoredHosts;

    public HeartbeatManager(HeartbeatListener listener) {
        this.listener = listener;
        monitoredHosts = new HashMap<>();
        executor = Executors.newCachedThreadPool();
    }

    public void kill() {
        executor.shutdown();
    }

    public void addHost(String hostId, int port) {
        try {
            if (monitoredHosts.get(hostId) == null) {
                Heartbeat heartbeat = new Heartbeat(port, this, hostId);
                monitoredHosts.put(hostId, heartbeat);
                executor.submit(heartbeat);
                listener.onAcquiredCommunication(new HeartbeatEvent(hostId, 0, new Date().getTime()));
            }
        } catch (IOException exc) {
            LOGGER.log(Level.SEVERE, exc::getMessage);
        }
    }

    public boolean removeFromMonitoredHost(String identifier) {
        Heartbeat monitor = monitoredHosts.remove(identifier);
        if (monitor != null) {
            monitor.kill();
            return true;
        }
        return false;
    }

    @Override
    public synchronized void update(HeartbeatState heartbeatState, HeartbeatEvent event) {
        switch (heartbeatState) {

            case HOST_OFFLINE:
                listener.onDeath(event);
                removeFromMonitoredHost(event.getSource());
                break;
            case HEARTBEAT_RECEIVED:
                listener.onHeartbeat(event);
                break;
            case HOST_ONLINE:
                listener.onReacquiredCommunication(event);
                break;
            case COMMUNICATION_LOST:
                listener.onLossCommunication(event);
                break;
            default:
                break;
        }
    }
}
