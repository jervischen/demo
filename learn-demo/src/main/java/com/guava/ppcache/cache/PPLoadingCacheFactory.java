package com.guava.ppcache.cache;

import com.guava.ppcache.bean.LoadingCacheConf;

import java.util.function.Function;

/**
 * @author Chen Xiao
 * @since 2021-10-13 16:00
 */
public class PPLoadingCacheFactory<K, V> {

    private final static Object lock = new Object();

    private static PPLoadingCacheFactory factory;


    PPLoadingCacheProxy<K, V> createSizePPLoadingfCache(String cacheName, LoadingCacheConf cacheConf, Function<K, V> function) {
        return new PPLoadingCacheProxy(new PPSizeLoadingCache(cacheName, cacheConf, function));
    }


    static PPLoadingCacheFactory getInstance() {
        if (factory == null) {
            synchronized (lock) {
                if (factory == null) {
                    factory = new PPLoadingCacheFactory();
                }
            }
        }
        return factory;
    }
}
