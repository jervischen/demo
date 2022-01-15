package com.example.demo.queue;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author Chen Xiao
 * @since 2019-10-10 16:51
 */
public class DelayQueueTest {
    public static void main(String[] args) {
        // 创建延时队列
        DelayQueue<Message> queue = new DelayQueue<Message>();

        // 添加延时消息,m1 延时3s
        Message m1 = new Message(2, "world", System.currentTimeMillis() + 3000);
        // 添加延时消息,m2 延时10s
        Message m2 = new Message(1, "hello", System.currentTimeMillis() + 10000);
        //将延时消息放到延时队列中
        queue.offer(m1);
        queue.offer(m2);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);

        exec.execute(new Consumer(queue));
        exec.shutdown();

        System.out.println(1111);
    }


    @Test
    public void testBlockingQueue(){
        BlockingQueue<String> workQueue = new LinkedBlockingDeque<>(100);
        workQueue.add("a");
        workQueue.add("b");
        workQueue.add("c");

        System.out.println(workQueue.poll());
    }
}
