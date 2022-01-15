package com.example.pool;

import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chen Xiao
 * @since 2021-09-17 00:41
 */
public class TestThreadPool {


    private final static AtomicInteger auto = new AtomicInteger();

    @Test
    public void threadstate() {
        System.out.println("线程池数量：" + (1 << 29));
    }

    @Test
    public void aa() {
        System.out.println(auto.getAndIncrement());
    }

    /**
     * 测试线程池异常
     * 异常后线程会销毁并新建一个work线程
     * <p>
     * 如果加了try catch则不销毁
     *
     * @throws IOException
     */
    @Test
    public void testTreadPoolException() throws IOException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(100), new ThreadFactory() {
            private final AtomicInteger au = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "t_pl_pool_" + au.getAndIncrement());
            }
        }/*, new ThreadPoolExecutor.DiscardOldestPolicy()*/);

        while (auto.get() < 10) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    int andIncrement = auto.getAndIncrement();
                    try {
                        if (andIncrement < 1) {
                            System.out.println("小于1" + Thread.currentThread().getName() + " flag=" + andIncrement);
                            System.out.println(1 / 0);
                        } else {
                            System.out.println("大于1" + Thread.currentThread().getName() + " flag=" + andIncrement);
                        }
                    } catch (Exception e) {

                    }

                }
            });
        }

//        System.in.read();
    }

    @Test
    public void testQue() {
        LinkedBlockingDeque deque = new LinkedBlockingDeque(5);
        deque.add("a");
        deque.add("b");
        deque.add("c");
        deque.add("d");
        System.out.println(deque.poll());


        System.out.println(getMinute());

    }

    public static int getMinute() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        return hour * 60 + min;
    }
}
