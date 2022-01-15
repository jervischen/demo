package com.example.demo.pool;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chen Xiao
 * @since 2020-07-24 22:13
 */
public class TestPool {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) {
        System.out.println(COUNT_BITS);
        System.out.println("二进制：" + Integer.toBinaryString(RUNNING)+  "  十进制："+RUNNING);
        System.out.println("二进制：" + Integer.toBinaryString(SHUTDOWN)+  "  十进制："+SHUTDOWN);
        System.out.println("二进制：" + Integer.toBinaryString(STOP) + "  十进制："+STOP);
        System.out.println(Integer.toBinaryString(TIDYING));
        System.out.println(Integer.toBinaryString(TERMINATED));
        System.out.println("队列大小：" + CAPACITY);
        System.out.println("队列大小：" + Integer.toBinaryString(CAPACITY));
        System.out.println("~队列大小：" + Integer.toBinaryString(~CAPACITY));
        System.out.println(runStateOf(1));
        System.out.println(new TestPool().ctl.get());
    }

    @Test
    public void testLinkedBlockingQueue(){
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue(2);

//        Object[] objects = new Object[Integer.MAX_VALUE-2000];
//        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(Integer.MAX_VALUE);
        linkedBlockingQueue.offer(1);
        linkedBlockingQueue.offer(2);
        linkedBlockingQueue.offer(3);
        System.out.println(linkedBlockingQueue);
        System.out.println(linkedBlockingQueue.remove());
//        System.out.println(linkedBlockingQueue.offer());

    }

    @Test
    public void testFinall(){
        try {
            try {
                System.out.println(111);
                throw new RuntimeException( " error!!!");
            }catch (Exception e){
                throw e;
            }finally {
                System.out.println("里层fianl");
            }

        }finally {
            System.out.println("最外层fianl");
        }
    }
}
