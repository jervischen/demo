package demo;

import org.redisson.api.RedissonClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Student {

    private RedissonClient redissonClient;

    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);


    public static void main(String[] args) {
        System.out.println(getThisWeek());

        System.out.println(0x170 + 0x0b);


    }


    public static String getThisWeek() {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(c.getTime());
    }
}
