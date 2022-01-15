package com.example.io.nio;

import java.util.Scanner;

/**
 * @author Chen Xiao
 * @since 2021-07-27 17:22
 */
public class NTest {
    //测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        //运行服务器
//        NServer.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        NClient.start();
        while(NClient.sendMsg(new Scanner(System.in).nextLine()));
    }
}
