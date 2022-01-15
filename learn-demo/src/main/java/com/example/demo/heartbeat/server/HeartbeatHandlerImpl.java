package com.example.demo.heartbeat.server;

import com.example.demo.heartbeat.Cmder;
import com.example.demo.heartbeat.HeartbeatHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created in 2019-04-20 10:16.
 *
 * @author chenxiao
 */
public class HeartbeatHandlerImpl implements HeartbeatHandler {
    private static Logger logger = LoggerFactory.getLogger(HeartbeatHandlerImpl.class);
    @Override
    public Cmder sendHeartBeat(HeartbeatEntity info) {
        HeartbeatLinstener linstener = HeartbeatLinstener.getInstance();

        // 添加节点
        if (!linstener.checkNodeValid(info.getNodeID())) {
            linstener.registerNode(info.getNodeID(), info);
        }

        // 其他操作
        Cmder cmder = new Cmder();
        cmder.setNodeID(info.getNodeID());
        // ...

        System.out.println("当前所有节点: ");
        Map<String, Object> nodes = linstener.getNodes();
        for (Map.Entry e : nodes.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        System.out.println("");
        return cmder;
    }
}
