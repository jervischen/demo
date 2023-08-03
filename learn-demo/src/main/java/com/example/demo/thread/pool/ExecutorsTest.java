package com.example.demo.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Xiao
 * @since 2019-09-23 20:42
 */
@Slf4j
public class ExecutorsTest {
    private ExecutorService streamThreadPool = Executors.newFixedThreadPool(1);


    public static void main(String[] args) {
        //SynchronousQueue
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //LinkedBlockingQueue
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);

        //DelayedWorkQueue
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //LinkedBlockingQueue
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
//                shutdownGracefully();
            }
        });
    }

    public void shutdownGracefully() {
        shutdownThreadPool(streamThreadPool, "main-pool");
    }

    /**
     * 优雅关闭线程池
     * @param threadPool
     * @param alias
     */
    private void shutdownThreadPool(ExecutorService threadPool, String alias) {
        log.info("Start to shutdown the thead pool: {}", alias);

        threadPool.shutdown(); // 使新任务无法提交.
        try {
            // 等待未完成任务结束
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow(); // 取消当前执行的任务
                log.warn("Interrupt the worker, which may cause some task inconsistent. Please check the biz logs.");

                // 等待任务取消的响应
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS))
                    log.error("Thread pool can't be shutdown even with interrupting worker threads, which may cause some task inconsistent. Please check the biz logs.");
            }
        } catch (InterruptedException ie) {
            // 重新取消当前线程进行中断
            threadPool.shutdownNow();
            log.error("The current server thread is interrupted when it is trying to stop the worker threads. This may leave an inconcistent state. Please check the biz logs.");

            // 保留中断状态
            Thread.currentThread().interrupt();
        }

        log.info("Finally shutdown the thead pool: {}", alias);
    }


}
