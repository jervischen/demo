package com.example.demo.heartbeat.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created in 2019-04-20 10:14.
 *
 * @author chenxiao
 */
public class HeartbeatEntity implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(HeartbeatEntity.class);


    private long time;
    private String nodeID;
    private String error;
    private Map<String, Object> info = new HashMap<String, Object>();

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
