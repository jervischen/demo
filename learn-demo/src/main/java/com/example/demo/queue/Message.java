package com.example.demo.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Xiao
 * @since 2019-10-10 16:48
 */
@Data
@AllArgsConstructor
public class Message implements Delayed {

    private int id;
    private String body; // 消息内容
    private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。



    // 自定义实现比较方法返回 1 0 -1三个参数
    @Override
    public int compareTo(Delayed delayed) {
        Message msg = (Message) delayed;
        return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1
                : (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
    }

    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.excuteTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
}
