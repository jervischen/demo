package com.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * @author Chen Xiao
 * @since 2020-10-18 14:26
 */
public class OnStackTest {
    public static void alloc(){
        byte[] b=new byte[2];
        b[0]=1;
    }
    public static void main(String[] args) {
        long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);

        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
    }

}
