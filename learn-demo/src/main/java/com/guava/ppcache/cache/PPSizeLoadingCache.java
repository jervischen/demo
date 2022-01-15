package com.guava.ppcache.cache;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import com.guava.ppcache.bean.LoadingCacheConf;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Chen Xiao
 * 基于容量
 * @since 2021-10-13 10:02
 */
@Slf4j
public class PPSizeLoadingCache<K, V> extends AbstractPPLoadingCache<K, V> {

    /**
     * 创建基于数量大小的缓存
     *
     * @return
     */
    @Override
    protected void createLoadingCache() {
        loadingCache = CacheBuilder.newBuilder()
                .recordStats()
                .removalListener((notify) -> {
                    if (!loadingCacheConf.isProduct()) {
                        log.info("{} cache [{}] remove key[{}],value[{}]", this.cacheName, notify.getCause().name(), notify.getKey(), notify.getValue());
                    }
                })
                .maximumSize(loadingCacheConf.getMaximumSize())
                //不会remove该key，下次访问会触发刷新，新值没有回来时返回旧值
                .refreshAfterWrite(loadingCacheConf.getRefreshAfterTime(), loadingCacheConf.isProduct() ? loadingCacheConf.getTimeUnit() : TimeUnit.SECONDS)
                //expire是remove该key，下次访问是同步去获取返回新值,避免refreshAfterWrite返回旧值
                .expireAfterWrite(loadingCacheConf.getExpireAfterTime(), loadingCacheConf.isProduct() ? loadingCacheConf.getTimeUnit() : TimeUnit.SECONDS)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K key) throws Exception {
                        return function.apply(key);
                    }

                    @Override
                    public ListenableFuture<V> reload(K key, V oldValue) throws Exception {
                        log.info("{}异步刷新........", Thread.currentThread().getName());
                        return refreshLocalCachePool.submit(() -> function.apply(key));
                    }
                });
    }


    private PPSizeLoadingCache() {

    }


    /**
     * 构造函数
     *
     * @param loadingCacheConf
     * @param function
     */
    PPSizeLoadingCache(String cacheName, LoadingCacheConf loadingCacheConf, Function<K, V> function) {
        Preconditions.checkArgument(StringUtils.isNotBlank(cacheName), "cacheName must be initialize...");
        this.cacheName = cacheName;

        Preconditions.checkArgument(loadingCacheConf.getRefreshAfterTime() != 0, "refreshAfterTime must be initialize...");
        Preconditions.checkArgument(loadingCacheConf.getExpireAfterTime() != 0, "expireAfterTime must be initialize...");
        Preconditions.checkArgument(loadingCacheConf.getTimeUnit() != null, "timeUnit must be initialize...");
        Preconditions.checkArgument(loadingCacheConf.getMaximumSize() != 0, "maximumSize must be initialize...");
        this.loadingCacheConf = loadingCacheConf;

        Preconditions.checkArgument(function != null, "function is not be null...");
        this.function = function;

        build();
    }
}
