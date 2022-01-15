package com.guava.ppcache.cache;

import com.google.common.base.Preconditions;
import com.guava.ppcache.bean.LoadingCacheConf;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author Chen Xiao
 * @since 2021-10-14 15:39
 */
@Slf4j
public class PPLoadingCacheManager<K, V> {

    private static PPLoadingCacheManager ppLoadingCacheManager;

    private final static Object lock = new Object();

    private final static Map<String, PPLoadingCacheProxy> loadingCacheMap = new ConcurrentHashMap<>(64);

    static {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            private final AtomicInteger au = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "ppLoadingCacheManager-sch" + au.getAndIncrement());
                return thread;
            }
        });
        log.info("开启缓存统计");
        /**
         * scheduleAtFixedRate 优先保证任务执行的频率
         * scheduleWithFixedDelay 优先保证任务执行的间隔
         */
        executor.scheduleWithFixedDelay(() -> PPLoadingCacheManager.allCacheStats(), 10, 10, TimeUnit.SECONDS);
    }


    public PPLoadingCacheProxy<K, V> createLoadingCache(String cacheName, LoadingCacheConf loadingCacheConf, Function<K, V> function) {
        PPLoadingCacheProxy<K, V> proxy = PPLoadingCacheFactory.getInstance().createSizePPLoadingfCache(cacheName, loadingCacheConf, function);
        loadingCacheMap.put(cacheName, proxy);

        return proxy;
    }

    private PPLoadingCacheManager() {

    }

    public PPLoadingCacheProxy getLoadingCacheProxy(String cacheName) {
        PPLoadingCacheProxy cacheProxy = loadingCacheMap.get(cacheName);
        Preconditions.checkNotNull(cacheProxy, String.format("%s is not init...", cacheName));
        return cacheProxy;
    }

    public static PPLoadingCacheManager getInstance() {
        if (ppLoadingCacheManager == null) {
            synchronized (lock) {
                if (ppLoadingCacheManager == null) {
                    ppLoadingCacheManager = new PPLoadingCacheManager();
                }
            }
        }

        return ppLoadingCacheManager;
    }

    public static PPLoadingCacheProxy getCacheByName(String cacheName) {
        PPLoadingCacheProxy proxy = loadingCacheMap.get(cacheName);
        Preconditions.checkNotNull(proxy, String.format("cache[%s] is not init...", cacheName));

        return proxy;
    }

    public static void allCacheStats() {
        for (PPLoadingCacheProxy proxy : loadingCacheMap.values()) {
            proxy.stats();
        }
    }
}
