package com.example.demo.heartbeat.client;

import com.example.demo.heartbeat.Cmder;
import com.example.demo.heartbeat.HeartbeatHandler;
import com.example.demo.heartbeat.server.HeartbeatEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * Created in 2019-04-20 10:13.
 *
 * @author chenxiao
 */
public class HeartbeatClient implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(HeartbeatClient.class);


    private String serverIP = "127.0.0.1";
    private int serverPort = 8089;
    private String nodeID = UUID.randomUUID().toString();
    private boolean isRunning = true;
    //  最近的心跳时间
    private long lastHeartbeat;
    // 心跳间隔时间
    private long heartBeatInterval = 10 * 1000;

    public void run() {
        try {
            while (isRunning) {
                HeartbeatHandler handler = RPCClient.getRemoteProxyObj(HeartbeatHandler.class, new InetSocketAddress(serverIP, serverPort));
                long startTime = System.currentTimeMillis();
                // 是否达到发送心跳的周期时间
                if (startTime - lastHeartbeat > heartBeatInterval) {
                    System.out.println("send a heart beat");
                    lastHeartbeat = startTime;

                    HeartbeatEntity entity = new HeartbeatEntity();
                    entity.setTime(startTime);
                    entity.setNodeID(nodeID);

                    // 向服务器发送心跳，并返回需要执行的命令
                    Cmder cmds = handler.sendHeartBeat(entity);

                    if (!processCommand(cmds))
                        continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean processCommand(Cmder cmds) {
        // ...
        return true;
    }

}
