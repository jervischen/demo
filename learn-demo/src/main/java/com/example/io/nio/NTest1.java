package com.example.io.nio;

import java.util.Scanner;

/**
 * @author Chen Xiao
 * @since 2021-07-27 17:22
 */
public class NTest1 {
    //测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        //运行客户端
        NClient.start();
        while(NClient.sendMsg(new Scanner(System.in).nextLine()));
    }
}
