package com.jvm;

/**
 * @author Chen Xiao
 * -Xms12m -Xmx12m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=/Users/chenxiao/jvm/c.dum -XX:+UseConcMarkSweepGC -XX:NewRatio=2
 * @since 2020-10-18 12:55
 */
public class TestHeap {


    public static void main(String[] args) throws InterruptedException {
//        List<byte[]> list = Lists.newArrayList();
//        int i = 0;
//        while (true) {
//            byte[] bytes = new byte[1024 * 1024 * 2];
////            list.add(bytes);
//            System.out.println("----->" + ++i);
//            Thread.sleep(100L);
//        }

        String s= "1#2##34";
        String[] split = s.split("#");
        System.out.println(split.length);
        for (String s1 : split) {
            System.out.println(s1);
        }
    }
}
