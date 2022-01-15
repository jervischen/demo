package com.example.demo.redis;

import com.example.demo.bean.Menu;
import com.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created in 2018-01-08 18:36.
 *
 * @author chenxiao
 */
public class TestRedis {
    private static Logger logger = LoggerFactory.getLogger(TestRedis.class);

    static Jedis jedis = null;

    static {
        //连接本地的 Redis 服务
//        jedis = new Jedis("pplive-6379-redis.lizhi.fm", 6379);
        jedis = new Jedis("localhost", 6379);
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }

    static String key = "LZ_FANSLEVEL_NJ_FANS_MEDAL_NAME_INFO";
    static String field = "2636074689511825452";

    public static void main(String[] args) {
//        delRedis();

//        zrangeByScore();

        testLua();

    }

    public static void zrangeByScore() {
        String key = "abc";
        jedis.del(key);
        jedis.zadd(key, 1.1, "a");
        jedis.zadd(key, 1, "b");
        jedis.zadd(key, 1.3, "c");
        jedis.zadd(key, 1.4, "d");

        Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key, "1", "1");
        for (Tuple tuple : tuples) {
            System.out.println(tuple.getScore());
        }
        System.out.println("==============");
        for (Tuple tuple : jedis.zrevrangeByScoreWithScores(key, 2, 1)) {
            System.out.println(tuple.getScore());
        }


    }

    public static void delRedis() {
        String key = "*5170857476530110591*";

        Set<String> keys = jedis.keys(key);
        for (String s : keys) {
            System.out.println(s);
            jedis.del(s);
        }
        jedis.close();
    }

    @Test
    public void a() {

        List<String> info = jedis.hmget("SC_ACT_WAKA_GAME_INFO", "5096622024198328447");
        System.out.println(info);
    }

    @Test
    public void b() {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");


        boolean flag = true;
        int i = 1;
        while (flag) {
            System.out.println(list.size());
            if (list.size() == 1) {
                flag = false;
            }
            list.remove("a");
        }

        Menu menu = new Menu();
        menu.getContent().setText("abc");
        System.out.println(menu);
    }


    @Test
    public void c() {
        boolean abc1 = StringUtils.isNumeric("abc");
        boolean abc = abc1;
        System.out.println(StringUtils.isNumeric("abc"));
        System.out.println(StringUtils.isNumeric(" 123"));
        System.out.println(StringUtils.isNumeric("123"));
    }


    public static void testLua() {

        long l = DateUtil.formatStrToNormalDate("2121-10-01 00:00:00").getTime() - System.currentTimeMillis();
        String tail = String.format("0.%d", l);

        String lua = " local key = KEYS[1] \n" +
                " local field = ARGV[1] \n" +
                " local score = ARGV[2] \n" +
                " local tail = ARGV[3] \n" +
                " local current_score = redis.call('zscore', key, field) \n" +
                " current_score = tonumber(current_score) or 0 \n" +
//                " print(current_score)\n" +
                " score = tonumber(score) or 0 \n" +
                " tail = tonumber(tail) or 0 \n" +
                " local result_score = math.floor(current_score) + score + tail \n" +
                " redis.call('zadd', key, result_score, field);" +
                " return result_score";
        Object result = jedis.evalsha(jedis.scriptLoad(lua), Arrays.asList("11"), Arrays.asList("2640877571050324524L", "6", tail));
        System.out.println((long)result);
    }

}
