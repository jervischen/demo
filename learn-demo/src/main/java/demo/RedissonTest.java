package demo;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class RedissonTest {

    public static void main(String[] args) {

        RedissonClient redissonClient = Redisson.create();

        RLock rLock = redissonClient.getLock("lockName");
        try {
            // hget lockName 3ea72634-7f9c-454f-a78a-ca2ab79f2e18:1
            // entryName=3ea72634-7f9c-454f-a78a-ca2ab79f2e18:lockName
            boolean isLocked = rLock.tryLock(100, TimeUnit.MILLISECONDS);

            Thread.sleep(5000);
            new Thread(new Runnable() {
                @Override
                public void run() {

                    rLock.tryLockAsync(Thread.currentThread().getId());
                }
            }).start();

            if (isLocked) {
                // TODO
                System.in.read();
//                rLock.tryLock();
//                rLock.unlock();
            }
        } catch (Exception e) {
        }finally {
            rLock.unlock();
        }
    }
}
