package com.example.io.bio;

import java.io.IOException;
import java.util.Random;

/**
 * @author Chen Xiao
 * @since 2021-07-27 16:09
 */
public class Test {
    //测试主方法
    public static void main(String[] args) throws InterruptedException, IOException {
        //运行客户端
        Random random = new Random(System.currentTimeMillis());
        //随机产生算术表达式
        int i = new Random().nextInt(100);
        Client.send(String.valueOf(i));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
