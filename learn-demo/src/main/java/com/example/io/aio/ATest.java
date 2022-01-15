package com.example.io.aio;

import com.example.io.aio.client.AClient;
import com.example.io.aio.server.AServer;

import java.util.Scanner;

/**
 * @author Chen Xiao
 * @since 2021-07-27 17:41
 */
public class ATest {
    //测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        //运行服务器
        AServer.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        AClient.start();
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while(AClient.sendMsg(scanner.nextLine()));
    }
}
