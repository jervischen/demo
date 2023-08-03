package com.jdk8.list;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionTest {

    public static List<Person> getList() {
        ArrayList<Person> objects = Lists.newArrayList();

        objects.add(Person.builder().id(1).name("a").build());
        objects.add(Person.builder().id(2).name("b").build());
        objects.add(Person.builder().id(3).name("c").build());
        objects.add(Person.builder().id(4).name("e").build());
        objects.add(Person.builder().id(4).name("d").build());
        return objects;
    }

    @Test
    public void testFilter() {
        Optional<Person> first = getList().stream().filter(p -> p.getId() == 4).findFirst();
        System.out.println(first.get());

    }

    @Test
    public void testPault() {
        // 用 forEach 替代
        Lists.newArrayList("1", "2", "8", "3", "4", "5").parallelStream().forEach(x -> {
            if (x.equals("8")) {
                //continue; // 不支持，提示 Continue outside of loop
                //break;// 不支持，提示 Break outside switch or loop
                return;// 其实 return 就是相当于传统的for循环的continue
            }
            System.out.println("处理其他业务逻辑--->" + x);
        });
    }

    @Test
    public void testParallelAdd() {
        ArrayList<Integer> integers = new ArrayList<>();
        IntStream.range(0, 8888).parallel().forEach(i -> {
            System.out.print(" " + i + " ");
            integers.add(i);
        });
        System.out.println(" ");
        System.out.println(integers);
    }

    /**
     * paralleStream并行 是否一定比 Stream串行 快？
     * - 错误，数据量少的情况，可能串行更快，ForkJoin会耗性能
     * - 多数情况下并行比串行快，是否可以都用并行?
     * - 不行，部分情况会有线程安全问题，parallelStream里面使用的外部变量，比如集合一定要使用线程安全集合，不然就会引发多线程安全问题
     * <p>
     * ### 多线程安全问题
     * - 比如：对没有线程安全的List集合进行添加操作，会出现问题。
     * - 代码
     */
    @Test
    public void testParallelAdd2() {
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> integers = new ArrayList<>();
            IntStream.range(0, 100).parallel().forEach(integers::add);
            System.out.println(integers);
        }
    }

    @Test
    public void testParallelRemove() {
        ArrayList<Integer> integers = new ArrayList<>();
        IntStream.range(0, 100).forEach(integers::add);
        System.out.println(integers);

        List<Integer> collect = integers.parallelStream().filter(i -> i == 1).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void teslist() throws Exception {
        for (int j = 0; j < 999; j++) {
            CountDownLatch c = new CountDownLatch(1);
            ArrayList<Integer> list = new ArrayList<>(55);
            for (int i = 0; i < 55; i++) {
                int finalI = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            c.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        list.add(finalI);
                    }
                }).start();
            }
            c.countDown();
            Thread.sleep(1200);
            if (list.size() <55){
                System.out.println(list.size());
                System.out.println(list);
            }
        }
    }
}
