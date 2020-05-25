package com.avijitmondal.together.alive.util;

import com.avijitmondal.together.alive.data.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class NetworkUtils {

    static synchronized byte[] receiveData(DatagramSocket datagramSocket) throws IOException {
        byte[] receiveData = new byte[Constants.UDP_VALID_PACKET_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        datagramSocket.receive(receivePacket);
        receiveData = receivePacket.getData();
        byte[] data = new byte[receivePacket.getLength()];
        System.arraycopy(receiveData, receivePacket.getOffset(), data, 0, receivePacket.getLength());
        return data;
    }
}
