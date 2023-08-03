package com.guava.ppcache.test;


import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.guava.ppcache.bean.LoadingCacheConf;
import com.guava.ppcache.bean.ThreadPoolConf;
import com.guava.ppcache.cache.PPLoadingCacheManager;
import com.guava.ppcache.cache.PPLoadingCacheProxy;
import com.guava.ppcache.executor.ExecutorFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Xiao
 * @since 2021-10-12 18:10
 */
@Slf4j
public class TestLoadingCache {
    /*音色*/
    private static final String VOICE_CACHE = "voice_cache";
    /*陪玩*/
    private static final String PLAYER_CACHE = "player_cache";
    /*是否陪玩*/
    private static final String IS_PLAYER_CACHE = "is_player_cache";
    final CountDownLatch latch = new CountDownLatch(1);

    public Optional<String> load(String str) {
        log.info("{} begin to mock query db...", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} success to mock query db...", Thread.currentThread().getName());
        return Optional.of(str);
    }

    public List<Long> loadInt(int code) {
        log.info("{} begin to mock query db... code[{}]", Thread.currentThread().getName(),code);
       if (code == 1){
           return Lists.newArrayList();
       }
//        log.info("{} success to mock query db...", Thread.currentThread().getName());
        return Lists.newArrayList(2L,3L,4L);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        TestLoadingCache t = new TestLoadingCache();
        t.testGetV();
    }

    public void testGetV() throws IOException {
        String cahce1 = TestLoadingCache.class.getName() + "_1";
        String cahce2 = TestLoadingCache.class.getName() + "_2";

        PPLoadingCacheProxy loadingCache = PPLoadingCacheManager.getInstance()
                .createLoadingCache(cahce1
                        , new LoadingCacheConf()
                        , s -> this.load((String) s));


        for (int i = 0; i < 10; i++) {
            new Thread(() -> getV(cahce1, "keyA")).start();
        }


        PPLoadingCacheManager.getInstance()
                .createLoadingCache(cahce2
                        , new LoadingCacheConf()
                        , s -> this.load((String) s));

        for (int i = 0; i < 10; i++) {
            new Thread(() -> getV(cahce2, "keyB")).start();
        }

        latch.countDown();
        System.in.read();
    }

    private void getV(String cacheName, String key) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            Optional<String> name = (Optional<String>) PPLoadingCacheManager.getCacheByName(cacheName).getUnchecked(key);
//            log.info("缓存[{}] value[{}]", cacheName, name);
        }
    }

    @Test
    public void clazzName() {
        String cahce1 = TestLoadingCache.class.getName() + "_1";
        System.out.println(cahce1);

        System.out.println(this.getClass().getSimpleName());
        System.out.println(this.getClass().getName());
    }


    @Test
    public void testExcutor() throws IOException {
        ThreadPoolExecutor threadPoolExecutor = ExecutorFactory.getInstance().create(new ThreadPoolConf()
                .setCorePoolSize(1)
                .setMaximumPoolSize(3)
                .setCapacity(3));

        for (int i = 1; i < 10; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                try {
                    System.out.println(finalI);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        System.in.read();
    }

    @Test
    public void getCache() throws InterruptedException {
       PPLoadingCacheManager.getInstance()
                .createLoadingCache(VOICE_CACHE
                        , new LoadingCacheConf()
                                .setRefreshAfterTime(60)
                                .setExpireAfterTime(60)
                                .setTimeUnit(TimeUnit.SECONDS)
                                .setMaximumSize(1_000)
                        , s -> this.loadInt((Integer) s));

        PPLoadingCacheManager.getInstance()
                .createLoadingCache(PLAYER_CACHE
                        , new LoadingCacheConf()
                                .setRefreshAfterTime(60)
                                .setExpireAfterTime(60)
                                .setTimeUnit(TimeUnit.SECONDS)
                                .setMaximumSize(1_000)
                        , s -> this.loadInt((Integer) s));

   PPLoadingCacheManager.getInstance()
                .createLoadingCache(IS_PLAYER_CACHE
                        , new LoadingCacheConf()
                                .setRefreshAfterTime(60)
                                .setExpireAfterTime(60)
                                .setTimeUnit(TimeUnit.SECONDS)
                                .setMaximumSize(1_000)
                        , s -> this.loadInt((Integer) s));



        while (true){
//            int i = new Random().nextInt(3) + 200;
            int i =  200;
            Thread.sleep(10000L);
            List<Long> unchecked = (List<Long>) PPLoadingCacheManager.getInstance().getLoadingCacheProxy(PLAYER_CACHE).getUnchecked(i);
            System.out.println("i=" + i + "  list=" + unchecked);
        }
    }

}
