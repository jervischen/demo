package com.example.demo.thread.dingshi;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author Chen Xiao
 * @since 2020-02-24 14:22
 */
public class ScheduledThreadPoolTest {

    public ScheduledThreadPoolExecutor scheduledThreadPool = new ScheduledThreadPoolExecutor(5);

    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolTest threadPoolTest = new ScheduledThreadPoolTest();
        // 创建大小为5的线程池

        for (int i = 0; i < 1; i++) {
            Task worker = new Task("task-" + i);

            ScheduledFuture<?> scheduledFuture = threadPoolTest.scheduledThreadPool.scheduleAtFixedRate(worker, 0, 5, TimeUnit.SECONDS);
            scheduledFuture.cancel(false);
        }

//        Thread.sleep(10000);
//
//        System.out.println("Shutting down executor...");
//        // 关闭线程池
//        threadPoolTest.scheduledThreadPool.shutdown();
//        boolean isDone;
//        // 等待线程池终止
//        do {
//            isDone = threadPoolTest.scheduledThreadPool.awaitTermination(1, TimeUnit.DAYS);
//            System.out.println("awaitTermination...");
//        } while(!isDone);

        System.out.println("Finished all threads");
    }
}



class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("name = " + name + ", startTime = " + new Date());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name = " + name + ", endTime = " + new Date());
    }

}