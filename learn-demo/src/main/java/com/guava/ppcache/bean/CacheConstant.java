package com.guava.ppcache.bean;

/**
 * @author Chen Xiao
 * @since 2021-10-15 19:44
 */
public class CacheConstant {

    public static String SEPARATOR = "-";


    /**
     * 线程名前缀
     */
    static String THREAD_NAME_PERFIX = "ppexecutor-worker-";

    /**
     * cpu数量
     */
    static int CPU_NUM =  Runtime.getRuntime().availableProcessors();

    /**
     * 默认队列大小
     */
    static int DEFAULT_CAPACITY = 10;

    /**
     * cache 刷新时间
     */
    static int REFRESH_AFTER_TIME = 10;

    /**
     * cache 过期时间
     */
    static int EXPIRE_AFTER_TIME = 20;

    /**
     * cache 大小
     */
    static int MAXIMUM_SIZE = 1_000;

    /**
     * 权重
     * 一般是限制缓存 字节大小
     */
    static long MAXIMUM_WEIGHT = 100 * 1024;
}
