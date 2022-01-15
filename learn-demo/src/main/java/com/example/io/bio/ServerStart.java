package com.example.io.bio;

import java.io.IOException;

/**
 * @author Chen Xiao
 * @since 2021-07-27 16:22
 */
public class ServerStart {

    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动");
        ServerNormal.start();
        System.out.println("服务端结束");
    }
}
