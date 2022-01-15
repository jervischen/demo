package com.example.demo.thread;

/**
 * @author Chen Xiao
 * @since 2020-02-24 15:48
 */

import java.util.concurrent.ScheduledExecutorService;

/**
 * 线程池创建. * @author wuhf * @date 2018/01/16
 */
public class ThreadPoolUtils {
    private static ScheduledExecutorService executorService;

    private ThreadPoolUtils() { //手动创建线程池.
//        executorService = new ScheduledThreadPoolExecutor(10, new BasicThreadFactory.Builder().namingPattern("syncdata-schedule-pool-%d").daemon(true).build());
    }

    private static class PluginConfigHolder {
        private final static ThreadPoolUtils INSTANCE = new ThreadPoolUtils();
    }

    public static ThreadPoolUtils getInstance() {
        return PluginConfigHolder.INSTANCE;
    }

    public ScheduledExecutorService getThreadPool() {
        return executorService;
    }
}

