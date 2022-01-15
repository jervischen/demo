package com.example.demo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2018-03-01 12:23.
 *
 * @author chenxiao
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args)  {
        MyThread thread = new MyThread();
        thread.start();
    }
}
class MyThread extends Thread{
    private static int num = 0;
    public MyThread(){
        num++;
    }
    @Override
    public void run() {
        System.out.println("主动创建的第"+num+"个线程");
    }
}
