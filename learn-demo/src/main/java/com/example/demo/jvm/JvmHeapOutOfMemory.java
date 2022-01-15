package com.example.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Xiao
 * -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
 * @since 2019-10-11 11:31
 */
public class JvmHeapOutOfMemory {
    /**
     * Jvm堆内存溢出
     */
    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();

        for (int i=0;i<10;i++) {
            System.out.println("第"+i+"次");
            list.add(new Byte[1*1024*1024]);
        }
        System.out.println("创建完毕！");

    }
}
