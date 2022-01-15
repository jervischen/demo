package com.guava.ppcache.cache;

import java.util.concurrent.ExecutionException;

/**
 * @author Chen Xiao
 * @since 2021-10-14 15:35
 */
public class PPLoadingCacheProxy<K, V> implements PPLoadingCache<K, V> {


    AbstractPPLoadingCache ppLoadingCache;


    private PPLoadingCacheProxy() {
    }

    PPLoadingCacheProxy(AbstractPPLoadingCache ppLoadingCache) {
        this.ppLoadingCache = ppLoadingCache;
    }

    @Override
    public void put(K key, V value) {
        ppLoadingCache.put(key,value);
    }

    @Override
    public V getUnchecked(K key) {
        return (V) ppLoadingCache.getUnchecked(key);
    }

    @Override
    public V get(K key) throws ExecutionException {
        return (V) ppLoadingCache.get(key);
    }

    @Override
    public void refresh(K key) {
        ppLoadingCache.refresh(key);
    }

    @Override
    public void invalidate(K key) {
        ppLoadingCache.invalidate(key);
    }

    @Override
    public void invalidateAll() {
        ppLoadingCache.invalidateAll();
    }

    @Override
    public void stats() {
        ppLoadingCache.stats();
    }
}
