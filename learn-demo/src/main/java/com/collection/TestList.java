package com.collection;

import com.google.common.collect.Lists;
import com.util.DateUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @author Chen Xiao
 * @since 2021-10-30 17:10
 */
public class TestList {

    static CountDownLatch count = new CountDownLatch(1);

    @Test
    public void iter() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(6);
        list.add(2);

//        removeLiest(list);

//        removeLiestIt(list);

        System.out.println(list);
        ArrayList<Integer> list2 = new ArrayList<>(list);
        System.out.println(list2);
        System.out.println(list.equals(list2));
        System.out.println(list == list2);

//        //多线程删除
//        for (int i = 0; i < 20; i++) {
//            new Thread(()->removeLiest2(list),i+"").start();
//            new Thread(()->removeLiest2(list),i+"").start();
//            new Thread(()->removeLiest2(list),i+"").start();
//        }
//
//
//        Thread.sleep(3000L);
//        count.countDown();
//
//        System.in.read();


        System.out.println(2640877571050324524L > 2646620839973229612L);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currMinute = calendar.get(Calendar.MINUTE);

        System.out.println(currMinute);
    }

    /**
     * IndexOutOfBoundsException
     * @param list
     */
    public static void removeLiest2(ArrayList<Integer> list) {
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始" + Thread.currentThread().getName());
       list.remove(2);
    }


    public static void removeLiestIt(ArrayList<Integer> list)  {
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始" + Thread.currentThread().getName());
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 2) {
                iterator.remove();
            }
        }
    }

    public static void removeLiest(ArrayList<Integer> list) {
        for (Integer integer : list) {
            if (integer == 2) {
                list.remove(integer);
                System.out.println(integer);
            }
        }
    }

    @Test
    public void tesStreamMap(){
        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");
        System.out.println(strings);
        System.out.println(strings.stream().map(Long::parseLong).collect(Collectors.toList()));
    }

    @Test
    public void tesStreamMa1p(){
//        System.out.println(Long.parseLong("123"));
        long l = DateUtil.formatStrToNormalDate("2121-10-01 00:00:00").getTime() - System.currentTimeMillis();
        System.out.println(l);
        String format = String.format("%d.0000%d", 10, l);
        Double aDouble = Double.valueOf(format);
        System.out.println(aDouble);
    }

}
