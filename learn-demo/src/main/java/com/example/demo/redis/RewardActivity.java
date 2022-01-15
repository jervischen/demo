package com.example.demo.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Chen Xiao
 * @since 2020-09-17 18:50
 */

public class RewardActivity {
    static Jedis jedis = null;

    @Before
    public void initRedis(){
        //连接本地的 Redis 服务
        jedis = new Jedis("pplive-6379-redis.lizhi.fm", 6379);
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }


    @Test
    public void initRank(){
        File file = new File("/rank.txt");
        InputStream in = null;
        try {
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
