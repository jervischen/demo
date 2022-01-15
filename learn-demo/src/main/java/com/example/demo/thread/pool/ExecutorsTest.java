package com.example.demo.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Chen Xiao
 * @since 2019-09-23 20:42
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        //SynchronousQueue
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //LinkedBlockingQueue
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);

        //DelayedWorkQueue
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //LinkedBlockingQueue
        ExecutorService executorService = Executors.newSingleThreadExecutor();


    }


}
