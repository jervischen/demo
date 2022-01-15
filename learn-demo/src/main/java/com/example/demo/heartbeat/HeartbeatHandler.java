package com.example.demo.heartbeat;

import com.example.demo.heartbeat.server.HeartbeatEntity;

/**
 * Created in 2019-04-20 10:16.
 *
 * @author chenxiao
 */
public interface HeartbeatHandler {
    public Cmder sendHeartBeat(HeartbeatEntity info);
}
