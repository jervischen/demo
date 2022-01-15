package simplicity.characterstring;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Xiao
 * @since 2021-11-16 13:14
 */
public class ReverseString {

    public static void main(String[] args) {
//        System.out.println(5213028454754077235L > 5213028257267321395L);
//
//        System.out.println(DateUtils.addYears(new Date(),-1));
//
        ArrayList<String> strings = Lists.newArrayList("a", "b", "c");
        System.out.println(strings.toArray());

        String[] strings1 = strings.toArray(new String[]{});
        System.out.println(strings1);
        a("a","b");

        System.out.println(24*60*60*2);
        System.out.println(TimeUnit.DAYS.toSeconds(1));
        System.out.println(TimeUnit.SECONDS.convert(2, TimeUnit.DAYS));
    }


    public void reverseString(char[] s) {

        for (int i = 0; i < s.length; i++) {

            int index = i+1/s.length;

            char tmp = s[index];


            s[index] = s[i];
        }

    }

    public static void a(String... a){
        System.out.println(a);
        for (String s : a) {
            System.out.println(s);
        }

    }

    @Test
    public void  a() throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        Thread.sleep(1000);
        watch.split();
        /*
         * This is the time between start and latest split.
         * 调用start()方法到最后一次调用split()方法耗用的时间
         */
        System.out.println(watch.getSplitTime());
        Thread.sleep(2000);
        watch.split();
        System.out.println(watch.getSplitTime());
        Thread.sleep(500);
        watch.stop();
        /*
         * This is either the time between the start and the moment this method
         * is called, or the amount of time between start and stop
         * 调用start()方法到调用getTime()或stop()方法耗用的时间
         */
        System.out.println(watch.getTime());
    }
}
