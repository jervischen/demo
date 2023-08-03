package com.hutool;

import cn.hutool.core.date.TimeInterval;
import org.junit.Test;

public class HuToolTest {


    @Test
    public void testInterval(){
        TimeInterval timeInterval = new TimeInterval();
        System.out.println(timeInterval.interval());
    }
}
