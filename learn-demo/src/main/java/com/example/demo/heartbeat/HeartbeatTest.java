package com.example.demo.heartbeat;

import com.example.demo.heartbeat.client.HeartbeatClient;
import com.example.demo.heartbeat.server.HeartbeatHandlerImpl;
import com.example.demo.heartbeat.server.ServiceCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created in 2019-04-20 10:17.
 *
 * @author chenxiao
 */
public class HeartbeatTest {
    private static Logger logger = LoggerFactory.getLogger(HeartbeatTest.class);
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //注册服务器的类
                    ServiceCenter serviceServer = ServiceCenter.getInstance();
                    serviceServer.register(HeartbeatHandler.class, HeartbeatHandlerImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread client1 = new Thread(new HeartbeatClient());
        client1.start();
        Thread client2 = new Thread(new HeartbeatClient());
        client2.start();
    }
}
