package com.avijitmondal.together.alive.service;

import com.avijitmondal.together.alive.model.HeartbeatEvent;

public interface HeartbeatListener {

    void onHeartbeat(HeartbeatEvent event);

    void onDeath(HeartbeatEvent event);

    void onLossCommunication(HeartbeatEvent event);

    void onReacquiredCommunication(HeartbeatEvent event);

    void onAcquiredCommunication(HeartbeatEvent event);
}
