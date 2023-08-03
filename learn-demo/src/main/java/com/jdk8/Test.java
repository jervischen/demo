package com.jdk8;

import com.alibaba.fastjson.JSONObject;
import com.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Xiao
 * @since 2021-06-24 20:09
 */
public class Test {
    @Data
    @AllArgsConstructor
    static
    class Aoo {
        private String name;
        private Integer age;
    }

    public static void main(String[] args) {

        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync2("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // 执行更多任务，比如查询其他商店
        doSomethingElse();
        // 在计算商品价格的同时
        try {
            Double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
            invocationTime = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Invocation returned after " + invocationTime + " msecs");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 访问其他商店
     */
    private static void doSomethingElse() {
        long a = 360000000;

    }

    @org.junit.Test
    public void testFilter(){
        JSONObject jsonObject = JSONObject.parseObject("{\"startTime\":\"2023-01-01 00:00:00\",\"endTime\":\"2023-04-01 00:00:00\"}");
        Date startTime = jsonObject.getDate("startTime");
        Date endTime = jsonObject.getDate("endTime");
        System.out.println(startTime);
        System.out.println(endTime);

        long a = 360000000;
        System.out.println(a);

        Date startDate = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7));
        startDate = DateUtil.getDayStart(startDate);
        Date endDate = new Date(startDate.getTime() + TimeUnit.DAYS.toMillis(7));
        System.out.println("accessLevelsStartDate="+ startDate +"  "+ endDate);
    }

}
