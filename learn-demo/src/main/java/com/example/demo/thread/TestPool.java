package com.example.demo.thread;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created in 2018-05-14 14:10.
 * 线程池
 * @author chenxiao
 */
public class TestPool {
    private static Logger logger = LoggerFactory.getLogger(TestPool.class);



    @Test
    public void a(){
        ArrayList<Integer> all = Lists.newArrayList(1, 2, 3, 4, 5);
        ArrayList<Integer> remove = Lists.newArrayList(1, 2, 3);
        all.removeAll(remove);
        System.out.println(all);

        long l = TimeUnit.SECONDS.toMillis(30);
        System.out.println(l);
        int id=202;


    }
}
