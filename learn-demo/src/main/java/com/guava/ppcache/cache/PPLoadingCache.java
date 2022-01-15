package com.guava.ppcache.cache;


import java.util.concurrent.ExecutionException;

/**
 * @author Chen Xiao
 * @since 2021-10-13 12:08
 */
public interface PPLoadingCache<K, V> {


    void put(K key, V value);

    V getUnchecked(K key);

    V get(K key) throws ExecutionException;

    void refresh(K key);

    void invalidate(K key);

    void invalidateAll();

    void stats();

}
