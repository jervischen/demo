package com.example.demo.optimizing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2018-03-09 13:57.
 *
 * @author chenxiao
 */
public class TestFrequency {
    private static Logger logger = LoggerFactory.getLogger(TestFrequency.class);

    public static void main(String[] args) {
        TestFrequency testFrequency = new TestFrequency();
        testFrequency.testLimit();
    }


    public void testLimit() {
        int i = 0;
        while (i < 5000) {
            i++;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    FrequencyUtils.limit("text_thread", 5000);
                }
            });
            t.start();
        }

        System.out.println("完成。。。");
    }
}
