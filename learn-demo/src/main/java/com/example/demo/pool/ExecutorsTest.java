package com.example.demo.pool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Chen Xiao
 * @since 2020-07-23 15:12
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor executor = init();
//        executor.execute(() -> sayHi("execute"));


        Future<?> future = executor.submit(() -> sayhello("submit"));
        Object o = null;
        try {
            o = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(o);
//        Thread.sleep(1000);
//        executor.execute(() -> sayHi("execute1"));
//        executor.submit(() -> sayHi("submit"));
    }

    public static void sayHi(String name) {
        String printStr = "thread-name:" + Thread.currentThread().getName() + ",执行方式：" + name;
        System.out.println(printStr);
        throw new RuntimeException(printStr + " error!!!");
    }

    public static void sayhello(String name) {
        String printStr = "thread-name:" + Thread.currentThread().getName() + ",执行方式：" + name;
        System.out.println(printStr);
        throw new RuntimeException(printStr + " error!!!");
    }

    private static ThreadPoolTaskExecutor init() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("thread_");
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(10);
        executor.setKeepAliveSeconds(1);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
