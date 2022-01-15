package com.example.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Created in 2018-03-13 10:01.
 *
 * @author chenxiao
 */
public class TestPeachDockerRedis {
    private static Logger logger = LoggerFactory.getLogger(TestPeachDockerRedis.class);

    static Jedis jedis = null;

    static {
        //连接本地的 Redis 服务
        jedis = new Jedis("172.17.6.232", 6379);
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    public static void main(String[] args) {
        TestPeachDockerRedis.getNjRank();
    }

    /**
     * 主播排行榜
     */
    public static void getNjRank() {

    }


}
