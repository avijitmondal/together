package com.avijitmondal.together.alive.service;

import com.avijitmondal.together.alive.model.HeartbeatEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HeartbeatHandler implements HeartbeatListener {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void onHeartbeat(HeartbeatEvent event) {
        logger.info("Received heartbeat from {}" + event.getSource() + " in " + event.getTimeFromPreviousBeat() + " at " + event.getBeatTimeStamp());
    }

    @Override
    public void onDeath(HeartbeatEvent event) {
        logger.info(event.getSource() + " died after " + event.getTimeFromPreviousBeat() + " at " + event.getBeatTimeStamp());
    }

    @Override
    public void onLossCommunication(HeartbeatEvent event) {
        logger.info("Communication lost of " + event.getSource() + " in " + event.getTimeFromPreviousBeat() + " at " + event.getBeatTimeStamp());
    }

    @Override
    public void onReacquiredCommunication(HeartbeatEvent event) {
        logger.info("Communication reacquired of " + event.getSource() + " in " + event.getTimeFromPreviousBeat() + " at " + event.getBeatTimeStamp());
    }

    @Override
    public void onAcquiredCommunication(HeartbeatEvent event) {
        logger.info(event.getSource() + " connected at " + event.getBeatTimeStamp());
    }
}
