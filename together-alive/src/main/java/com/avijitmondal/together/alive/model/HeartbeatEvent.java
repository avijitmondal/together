package com.avijitmondal.together.alive.model;

public class HeartbeatEvent  {

    private final String source;
    private final int timeElapsedPreviousBeat;
    private final long timeStamp;

    public HeartbeatEvent(String source, int timeElapsedPreviousBeat, long timeStamp) {
        this.source = source;
        this.timeElapsedPreviousBeat = timeElapsedPreviousBeat;
        this.timeStamp = timeStamp;
    }

    public String getSource() {
        return source;
    }

    public int getTimeFromPreviousBeat() {
        return timeElapsedPreviousBeat;
    }

    public long getBeatTimeStamp() {
        return timeStamp;
    }
}
