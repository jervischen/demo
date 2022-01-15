package com.jdk8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author Chen Xiao
 * @since 2021-06-24 20:09
 */
public class Shop {
    private static final Random random = new Random();

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    /**
     * 获得价格
     */
    public double getPrice(String product) {
        // 待实现
        return calculatePrice(product);
    }

    /**
     * 获得价格（异步 - 版本1）
     */
    public Future<Double> getPriceAsync1(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                // 正常计算技术，完成这次Future操作
                futurePrice.complete(price);
            } catch (Exception e) {
                // 抛出导致失败的异常，完成这次Future操作
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }
    /**
     * 获得价格（异步 - 版本2）
     */
    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    /**
     * 计算价格
     */
    private double calculatePrice(String product) {
        delay();
        // 模拟计算
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 延迟1s
     */
    public static void delay() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
