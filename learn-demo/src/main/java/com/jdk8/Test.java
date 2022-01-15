package com.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Future;

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
    @org.junit.Test
    private static void doSomethingElse() {


    }


}
