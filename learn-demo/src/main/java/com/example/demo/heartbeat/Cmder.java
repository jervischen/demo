package com.example.demo.heartbeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created in 2019-04-20 10:14.
 * 客户端需要执行的命令（由服务端返回）
 * @author chenxiao
 */
public class Cmder implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(Cmder.class);
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
}
