package com.avijitmondal.together.alive.data;

public final class Constants {

    private Constants() {
        throw new IllegalStateException("Constant class");
    }
    public static final int TIME_INTERVAL = 1000;
    public static final int TIME_LIFE_FRAMES = 5;
    public static final int TIME_LOSS_COMMUNICATION_FRAME = 3;
    public static final int UDP_VALID_PACKET_SIZE = 1024;
}
