package com.jdk8;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Chen Xiao
 * @since 2021-08-07 19:15
 */
public class CtFutureDemo {
    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()-> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        return future;
    }
    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> f = compute();
        class Client extends Thread {
            CompletableFuture<Integer> f;
            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }
            @Override
            public void run() {
                try {

                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        new Client("Client1", f).start();
        new Client("Client2", f).start();
        f.complete(111);
//        System.out.println("waiting");
//        f.whenCompleteAsync((v,e)->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("前return value:"+v+"  exception:"+e);
//        });
//
//
//        //设置Future.get()获取到的值
////        f.complete(111);
//        //以异常的形式触发计算
//        //f.completeExceptionally(new Exception());
//        f.whenCompleteAsync((v,e)->{
//            System.out.println("后return value:"+v+"  exception:"+e);
//        });
//
//        Thread.sleep(5000);
    }

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<Integer> future3 = future.thenApply((element)->{
            return element+1;
        }).thenApply((element)->{
            return element+2;
        });
        System.out.println(future3.get());//hello world  addPart  addTwoPart
    }
}
