package com.example.demo.heartbeat.server;

import com.example.demo.heartbeat.HeartbeatHandler;

import java.io.IOException;

/**
 * Created in 2019-05-22 12:45.
 *
 * @author chenxiao
 */
public class ServerBoostrap {
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
    }

}
