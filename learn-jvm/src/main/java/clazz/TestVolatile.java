package clazz;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Chen Xiao
 * @since 2021-09-27 13:27
 */
public class TestVolatile {

    public final int finalNum = 1;

    private volatile int volatileNum = 1;

    public void incr() {
        volatileNum++;
    }

    public static void main(String[] args) throws InterruptedException {
        while (true){
            TestVolatile testVolatile = new TestVolatile();

            Thread thread1 = new Thread(() -> testVolatile.incr());
            Thread thread2 = new Thread(() -> testVolatile.incr());
            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
            System.out.println(testVolatile.volatileNum);
        }
    }

}
