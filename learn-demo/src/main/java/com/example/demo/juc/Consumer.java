package com.example.demo.juc;

import java.util.concurrent.DelayQueue;

/**
 * Created in 2019-04-01 19:57.
 *
 * @author chenxiao
 */
public class Consumer implements Runnable{
    //延时队列 ,消费者从其中获取消息进行消费
    private DelayQueue<Message> queue;

    public Consumer(DelayQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(System.currentTimeMillis() / 1000);
                Message take = queue.take();
                System.out.println("消费消息id：" + take.getId() + " 消息体：" + take.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
