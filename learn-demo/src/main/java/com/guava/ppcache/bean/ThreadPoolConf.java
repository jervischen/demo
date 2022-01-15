package com.guava.ppcache.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Xiao
 * @since 2021-10-12 18:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ThreadPoolConf {

    /**
     * 存活时间
     */
    private long keepAliveTime = 0L;

    /**
     * 线程池容量
     * 容量满了才创建maximumPoolSize
     */
    private int capacity = CacheConstant.DEFAULT_CAPACITY;

    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    private int corePoolSize = CacheConstant.CPU_NUM;

    private int maximumPoolSize = CacheConstant.CPU_NUM;

    private String threadName = CacheConstant.THREAD_NAME_PERFIX;

    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
}
