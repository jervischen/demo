package com.example.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chen Xiao
 * @since 2021-07-31 09:23
 */
public class TestLock {

    static AtomicInteger at = new AtomicInteger();
    public static void main(String[] args) {
        System.out.println(at.get());

        System.out.println(at.getAndAdd(1));
        System.out.println(at.get());
        System.out.println(at.incrementAndGet());
    }
}
