package com.example.json;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Xiao
 * @since 2020-10-16 15:50
 */
public class MainDemo {


    /**
     * 线程池：刷新本地缓存
     * //
     */
//    private final ListeningExecutorService refreshLocalCachePool = MoreExecutors.listeningDecorator(
//            Executors.newFixedThreadPool(50, new ExecutionContextImpl.DefaultThreadFactory(getClass().getSimpleName())));
//
    public static void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("a", "1");
        map.put("b", "1");
        map.put("c", "1");
        map.put("5", "1");
        map.put("6", "1");
        System.out.println("map大小=" + map.size());
        for (final Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getKey());
        }

        System.out.println(2 << 3);

        for (int i = 16; i < 100; i++) {
            System.out.println(i % 16);
        }

    }

    @Test
    public void test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isNew", false);

        System.out.println(jsonObject.get("A"));
        System.out.println(jsonObject.getInteger("A"));
        System.out.println("false".equals(jsonObject.getString("isNew")));

        System.out.println(new Date().getHours());
        System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        System.out.println(1605787920L % 720);
        System.out.println(1605788640L % 720);
        System.out.println(1605788160L / 60 % (720 / 60));
        System.out.println(1605790080L / 60 % (720 / 60));

        List<String> list = Lists.newArrayList("1", "2");
        join(list.toArray(new String[0]));
        join("2", "2");

        JSONObject jsonObject1 = JSONObject.parseObject("");
        System.out.println(jsonObject1);

    }

    private void join(String... abc) {
        System.out.println(abc);
    }

    @Test
    public void testSeconde() throws InterruptedException {
//        while (true){
//            int i = Calendar.getInstance().get(Calendar.m);
//            System.out.println(i);
//            Thread.sleep(1000 * 60);
//        }

//        DateTime.Property property = new DateTime();
//        System.out.println(property.get());
//
//        System.out.println(new DateTime().withMillisOfDay(property.get() * 60 * 1000));

        System.out.println(Joiner.on("_").join(1, new Object[]{1, 23}));

        long millis = new DateTime().withHourOfDay(17).withMinuteOfHour(10).withSecondOfMinute(0).getMillis();
        System.out.println(millis);
    }

    @Test
    public void time() {
//        int startHour = 20;
//        int interval = 12;
//        DateTime.Property property = new DateTime().withHourOfDay(startHour).withMinuteOfHour(0).minuteOfDay();
//        int startMinute = property.get();
//        int nowMinute = new DateTime().minuteOfDay().get();
//
//        int downMinute = 0;
//        //20点后开始12分倒计时一次
//        int nowStage = (nowMinute - startHour) / interval;
//        downMinute = startHour + (nowStage + 1) * interval;
//
//        long timeDown = (downMinute - nowMinute) * 60 * 1000;
//        System.out.println(timeDown);

        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        System.out.println(minute % 5);
    }
}
