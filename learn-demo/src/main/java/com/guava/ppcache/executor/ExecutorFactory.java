package com.guava.ppcache.executor;


import com.guava.ppcache.bean.ThreadPoolConf;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chen Xiao
 * 线程执行器工厂
 * @since 2021-10-12 16:38
 */
@Slf4j
public class ExecutorFactory {

    private static ExecutorFactory executorFactory = null;

    private final static Object lock = new Object();

    /**
     * 创建线程池
     * @param threadPoolConf
     * @return
     */
    public ThreadPoolExecutor create(ThreadPoolConf threadPoolConf) {
        return new ThreadPoolExecutor(threadPoolConf.getCorePoolSize(),
                threadPoolConf.getMaximumPoolSize(),
                threadPoolConf.getKeepAliveTime(),
                threadPoolConf.getTimeUnit(),
                new LinkedBlockingQueue<>(threadPoolConf.getCapacity()),
                new ThreadFactory() {
                    private final AtomicInteger au = new AtomicInteger();

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r, threadPoolConf.getThreadName() + au.getAndIncrement());
                        log.info("创建线程[{}]", thread.getName());
                        return thread;
                    }
                }
                , threadPoolConf.getRejectedExecutionHandler()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
//                log.info("执行之前,线程worker[{}],提交线程[{}]", t.getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {

            }
        };
    }


    /**
     * 创建线程池
     * @return
     */
    public ThreadPoolExecutor create() {
        return create(new ThreadPoolConf());
    }

    public static ExecutorFactory getInstance() {
        if (executorFactory == null) {
            synchronized (lock) {
                if (executorFactory == null) {
                    executorFactory = new ExecutorFactory();
                }
            }
        }
        return executorFactory;
    }
}
