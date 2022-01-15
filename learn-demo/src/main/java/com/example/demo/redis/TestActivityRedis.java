package com.example.demo.redis;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Created in 2018-03-14 14:19.
 *
 * @author chenxiao
 */
public class TestActivityRedis {
    private static Logger logger = LoggerFactory.getLogger(TestActivityRedis.class);

    static Jedis jedis = null;

    @Before
   public void initRedis(){
       //连接本地的 Redis 服务
       jedis = new Jedis("172.17.6.232", 6379);
       //查看服务是否运行
       System.out.println("服务正在运行: " + jedis.ping());
   }

   @Test
   public void addNjRank(){
        String key = "LZ_LIVE_ACTIVITY_RANK_SORTED_SET_PEACH_20180314_3";
        int i=0;
        while (i < 50){
            i++;
          jedis.zincrby(key,i,i+"");
        }
   }
}
