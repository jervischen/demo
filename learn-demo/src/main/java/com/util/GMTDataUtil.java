package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/**
 * @author Chen Xiao
 * @since 2019-09-25 12:41
 */
public class GMTDataUtil {
    public final static String GMT0 = "GMT+0:00";
    public final static String GMT3 = "GMT+3:00";
    public final static String GMT8 = "GMT+8:00";

    /**
     * 显示到秒
     */
    public final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 显示到毫秒
     */
    public final static String MILLIS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 显示到天
     */
    public final static String DATE_REDIS_PATTERN = "yyyyMMdd";

    public final static String DATE_REDIS_PATTERN2 = "MMdd";

    public static String getGMT3CurrentTimeStr(String pattern) {
        return getCurrentTimeStr(GMT3, pattern);
    }

    public static String getCurrentTimeStr(String timeZoneId, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String getCurrentTimeStr(String timeZoneId, String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        return sdf.format(date);
    }

    public static Date getGMT3CurrentTime() {
        //取得指定时区的时间：　　　　　　
        TimeZone zone = TimeZone.getTimeZone(GMT3);
        Calendar c = Calendar.getInstance(zone);
        return c.getTime();
    }

    public static Date getCurrentTime(String timeZoneId) {
        //取得指定时区的时间：　　　　　　
        TimeZone zone = TimeZone.getTimeZone(timeZoneId);
        Calendar c = Calendar.getInstance(zone);
        return c.getTime();
    }

    public static void main(String[] args) {
        System.out.println(getCurrentTimeStr(GMT0, DEFAULT_PATTERN));
        System.out.println(getCurrentTimeStr(GMT3, DEFAULT_PATTERN));
        System.out.println(getCurrentTimeStr(GMT8, DEFAULT_PATTERN));

        Random random = new Random(1);
        for (int i=0;i <5;i++){
            System.out.println(random.nextInt(100));
        }
    }

}
