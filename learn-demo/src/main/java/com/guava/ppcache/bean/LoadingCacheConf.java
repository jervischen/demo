package com.guava.ppcache.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.TimeUnit;

/**
 * @author Chen Xiao
 * @since 2021-10-12 16:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoadingCacheConf {

    /**
     * 刷新时间
     * 不删除数据
     * cache没有数据会阻塞，只有一条线程去(load方法)获取值
     */
    private int refreshAfterTime = CacheConstant.REFRESH_AFTER_TIME;

    /**
     * 过期时间
     * 被动删除
     */
    private int expireAfterTime = CacheConstant.EXPIRE_AFTER_TIME;

    /**
     * 大小
     */
    private int maximumSize = CacheConstant.MAXIMUM_SIZE;

    /**
     * 权重
     * 一般是限制缓存 字节大小
     */
    private long maximumWeight = CacheConstant.MAXIMUM_WEIGHT;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    private String SEPARATOR = CacheConstant.SEPARATOR;

    /**
     * 用于测试,缓存时间默认
     */
    private boolean product = false;


}
