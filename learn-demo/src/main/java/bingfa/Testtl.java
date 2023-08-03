package bingfa;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Testtl {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> tl01 = new ThreadLocal<>();
        tl01.set("a");
        ThreadLocal<String> tl02 = new ThreadLocal<>();
        tl02.set("b");
        ThreadLocal<String> tl03 = new ThreadLocal<>();
        tl03.set("c");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    @Test
    public void testLongAdder(){
        LongAdder adder= new LongAdder();
        adder.increment();
    }


    public void testReentrantReadWriteLock(){
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rw.readLock();
        readLock.lock();

    }
}
