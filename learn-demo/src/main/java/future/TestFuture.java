package future;

import org.junit.Test;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建一个 CompletableFuture 实例来异步获取远程接口的结果
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            RemoteInterface1 remoteInterface1 = new RemoteInterface1Impl();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 调用远程接口1的getInterface1Result方法
            return remoteInterface1.getInterface1Result();
        });

        // 创建第二个 CompletableFuture 实例来异步获取另一个远程接口的结果
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            RemoteInterface2 remoteInterface2 = new RemoteInterface2Impl();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 调用远程接口2的getInterface2Result方法
            return remoteInterface2.getInterface2Result();
        });
        // 合并两个 CompletableFuture 实例的结果，以获取所有远程接口的结果
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);
        CompletableFuture<String> future = combinedFuture.thenApply(v -> {
            try {
                return future1.get() + "0000" + future2.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return "error";
        });

        // 阻塞当前线程，等待两个 CompletableFuture 实例的结果
        System.out.println(future.get());
        future.join();
        // 从 future1 和 future2 中获取远程接口的调用结果
        String result1 = future1.get();
        String result2 = future2.get();

        System.out.println("Interface1 result: " + result1);
        System.out.println("Interface2 result: " + result2);
    }


    @Test
    public void testNoBlock() throws Exception {
        // 创建异步 CompletableFuture 实例
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 模拟远程接口调用并返回结果
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, CompletableFuture!";
        });

        // 使用 thenAccept() 方法异步处理 CompletableFuture 对象
        future.thenAccept(s -> System.out.println("Received result: " + s));

        // 使用 thenApply() 方法异步处理 CompletableFuture 对象，并返回新的 CompletableFuture 对象
        CompletableFuture<String> newFuture = future.thenApply(s -> s.toUpperCase());

        // 使用 thenAccept() 方法异步处理新的 CompletableFuture 对象
        newFuture.thenAccept(s -> System.out.println("Transformed result: " + s));

        // 非阻塞
        System.out.println("This does not block");
        System.in.read();
    }
    @Test
    public void a(){
        int a = 0x01;
        int b = 0x03;
        System.out.println(a&b);

        String str = "abc 12";
        System.out.println(str.toLowerCase(Locale.ROOT));

    }
}
