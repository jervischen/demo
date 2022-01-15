package com.example.io.nio;

/**
 * @author Chen Xiao
 * @since 2021-07-27 16:56
 */
public class NServer {
    private static int DEFAULT_PORT = 8001;
    private static NServerHandle serverHandle;
    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle!=null)
            serverHandle.stop();
        serverHandle = new NServerHandle(port);
        new Thread(serverHandle,"NServer").start();
    }
    public static void main(String[] args){
        start();
    }

}
