package com.example.demo.heartbeat.client;

/**
 * Created in 2019-05-22 12:47.
 *
 * @author chenxiao
 */
public class ClientBoostrap {
    public static void main(String[] args) {
        Thread client1 = new Thread(new HeartbeatClient());
        client1.start();
        Thread client2 = new Thread(new HeartbeatClient());
        client2.start();
    }
}
