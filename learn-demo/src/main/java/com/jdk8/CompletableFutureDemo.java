package com.jdk8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Xiao
 * @since 2021-08-07 19:01
 */
public class CompletableFutureDemo {


    public static void main(String[] args) throws Exception {
        test01();
    }

    /**
     * 在Java8中，CompletableFuture提供了非常强大的Future的扩展功能，可以帮助我们简化异步编程的复杂性，
     * 并且提供了函数式编程的能力，可以通过回调的方式处理计算结果，也提供了转换和组合 CompletableFuture 的方法
     *
     *  注意: 方法中有Async一般表示另起一个线程,没有表示用当前线程
     */
    public static void test01() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(5);
        /**
         *  supplyAsync用于有返回值的任务，
         *  runAsync则用于没有返回值的任务
         *  Executor参数可以手动指定线程池，否则默认ForkJoinPool.commonPool()系统级公共线程池
         */
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "侯征";
        }, service);
        CompletableFuture<Void> data = CompletableFuture.runAsync(() -> System.out.println("侯征"));
        /**
         * 计算结果完成回调
         */
        future.whenComplete((x,y)-> System.out.println(x+","+y)); //执行当前任务的线程执行继续执
        data.whenCompleteAsync((x,y)-> System.out.println(x+","+y)); // 交给线程池另起线程执行
        future.exceptionally(Throwable::toString);
        //System.out.println(future.get());
        /**
         * thenApply,一个线程依赖另一个线程可以使用,出现异常不执行
         */
        //第二个线程依赖第一个的结果
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 5).thenApply(x -> x);

        /**
         * handle 是执行任务完成时对结果的处理,第一个出现异常继续执行
         */
        CompletableFuture<Integer> future2 = future1.handleAsync((x, y) -> x + 2);
        System.out.println(future2.get());//7
        /**
         * thenAccept 消费处理结果,不返回
         */
        future2.thenAccept(System.out::println);
        /**
         * thenRun  不关心任务的处理结果。只要上面的任务执行完成，就开始执行
         */
        future2.thenRunAsync(()-> System.out.println("继续下一个任务"));
        /**
         * thenCombine 会把 两个 CompletionStage 的任务都执行完成后,两个任务的结果交给 thenCombine 来处理
         */
        CompletableFuture<Integer> future3 = future1.thenCombine(future2, Integer::sum);
        System.out.println(future3.get()); // 5+7=12
        /**
         * thenAcceptBoth : 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
         */
        future1.thenAcceptBothAsync(future2,(x,y)-> System.out.println(x+","+y)); //5,7
        /**
         * applyToEither
         * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作
         */
        CompletableFuture<Integer> future4 = future1.applyToEither(future2, x -> x);
        System.out.println(future4.get()); //5
        /**
         * acceptEither
         * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作
         */
        future1.acceptEither(future2, System.out::println);
        /**
         * runAfterEither
         * 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable
         */
        future1.runAfterEither(future,()-> System.out.println("有一个完成了,我继续"));
        /**
         * runAfterBoth
         * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
         */
        future1.runAfterBoth(future,()-> System.out.println("都完成了,我继续"));
        /**
         * thenCompose 方法
         * thenCompose 方法允许你对多个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
         * thenApply是接受一个函数,thenCompose是接受一个future实例,更适合处理流操作
         */
        future1.thenComposeAsync(x->CompletableFuture.supplyAsync(()->x+1))
                .thenComposeAsync(x->CompletableFuture.supplyAsync(()->x+2))
                .thenCompose(x->CompletableFuture.runAsync(()-> System.out.println("流操作结果:"+x)));
        TimeUnit.SECONDS.sleep(5);//主线程sleep,等待其他线程执行
    }
}
