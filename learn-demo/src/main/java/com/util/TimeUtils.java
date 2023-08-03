package com.util;

import lombok.NonNull;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static final String DAY_PATTERN = "yyyyMMdd";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final int ONE_MONTH_SECONDS = 31 * 24 * 60 * 60;
    public static final long ONE_DAY_MILLS_SECONDS = 24 * 60 * 60 * 1000L;

    /**
     * 获取时间戳对应的毫秒数，时间格式：yyyy-MM-dd
     *
     * @param time 时间
     * @return
     */
    public static long getMillis(@NonNull String time) {
        return getMillis(time, DATE_PATTERN);
    }

    /**
     * 获取时间戳对应的毫秒数
     *
     * @param time    时间
     * @param pattern 时间格式
     * @return
     */
    public static long getMillis(@NonNull String time, @NonNull String pattern) {
        return DateTime.parse(time, DateTimeFormat.forPattern(pattern)).getMillis();
    }

    /**
     * 格式化时间
     *
     * @param millis  毫秒数
     * @param pattern 时间格式
     * @return
     */
    public static String format(long millis, @NonNull String pattern) {
        return new DateTime(millis).toString(pattern);
    }

    /**
     * 格式化时间，时间格式：yyyy-MM-dd
     *
     * @param millis 毫秒数
     * @return
     */
    public static String format(long millis) {
        return new DateTime(millis).toString(DATE_PATTERN);
    }

    /**
     * 计算下个月的开始时间
     *
     * @return
     */
    public static Date calStartOfNextMonth() {
        LocalDate today = new LocalDate();
        LocalDate firstDayOfMonth = today.plusMonths(1).withDayOfMonth(1);
        return new Date(firstDayOfMonth.toDateTimeAtStartOfDay().getMillis());
    }

    /**
     * 计算下2个月的开始时间
     *
     * @return
     */
    public static Date calStartOfNextTwoMonth() {
        LocalDate today = new LocalDate();
        LocalDate firstDayOfMonth = today.plusMonths(2).withDayOfMonth(1);
        return new Date(firstDayOfMonth.toDateTimeAtStartOfDay().getMillis());
    }

    /**
     * 计算当月的开始时间
     *
     * @return
     */
    public static Date calStartOfCurrentMonth() {
        LocalDate today = new LocalDate();
        LocalDate firstDayOfMonth = today.plusMonths(0).withDayOfMonth(1);
        return new Date(firstDayOfMonth.toDateTimeAtStartOfDay().getMillis());
    }

    /**
     * 计算上个月的开始时间
     *
     * @return
     */
    public static Date calStartOfPreviousMonth() {
        LocalDate today = new LocalDate();
        LocalDate firstDayOfMonth = today.plusMonths(-1).withDayOfMonth(1);
        return new Date(firstDayOfMonth.toDateTimeAtStartOfDay().getMillis());
    }

    /**
     * 计算当天开始的时间戳
     *
     * @param timestamp 请求时间戳
     * @param delay     延迟时间, 单位秒（对当天0点的扩展，往前扩展是负数，往后扩展是整数）
     * @return
     */
    public static Date calStartOfDay(long timestamp, long delay) {
        DateTime dateTime = new DateTime(timestamp).withMillisOfDay(0);
        long time = dateTime.getMillis() + delay * 1000;
        return new Date(time);
    }

    /**
     * 计算当天结束时间戳 xx日23点59分59秒
     *
     * @param timestamp 请求时间戳
     * @return
     */
    public static Date calEndOfDay(long timestamp) {
        DateTime dateTime = new DateTime(timestamp).withMillisOfDay(0).plusDays(1).minusSeconds(1);
        long time = dateTime.getMillis();
        return new Date(time);
    }

    /**
     * 获取一年中第几周
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        return Integer.valueOf(calendar.getWeekYear() + "" + String.format("%02d", weekOfYear));
    }

    public static void main(String[] args) {
        System.out.println(5000 *99/10);
        System.out.println(getWeek(new Date()));


    }
}