package com.example.demo.optimizing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created in 2018-03-09 13:56.
 *
 * @author chenxiao
 */
public class FrequencyUtils {
    private static Logger logger = LoggerFactory.getLogger(FrequencyUtils.class);
    private static volatile Map<String, AtomicInteger> map = new ConcurrentHashMap<>();

    static {
        new Timer("frequency-clear").scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    for (Map.Entry<String, AtomicInteger> entry : map.entrySet()) {
                        long seconds = System.currentTimeMillis() / 1000;
                        if (!entry.getKey().contains(String.valueOf(seconds))) {
                            map.remove(entry.getKey());
                            logger.info("clear frequency key:{}, size:{}", entry.getKey(), map.size());
                        }
                    }
                } catch (Exception e) {
                    logger.error("clear frequency map key error, size:{}", map.size(), e);
                }
            }
        }, 0, 1000);
    }

    /**
     * 秒级限流
     *
     * @param keyPrefix key前缀
     * @param limit     阈值
     * @return
     */
    public static boolean limit(String keyPrefix, long limit) {
        long seconds = System.currentTimeMillis() / 1000;
        String key = keyPrefix + "_" + seconds;
        AtomicInteger atomicInteger = map.get(key);

        // 先不做同步，试试效果
        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger(0);
            System.out.println(atomicInteger.get());
            map.put(key, atomicInteger);
        }
        int count = atomicInteger.getAndIncrement();
        System.out.println(count);
        if (count >= limit) {
            logger.info("The frequency exceeds the limit. key:{}, limit:{}, count:{}", key, limit, count);
            return true;
        }
        return false;
    }
}
