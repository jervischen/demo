package com.example.quartz;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chen Xiao
 * @since 2020-09-16 17:20
 */
public class SimpleTriggerExample {

    public static void main(String[] args) throws Exception {
        //创建工作
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withDescription("工作的描述")
                .withIdentity("工作的名称", "工作的组")
                .build();

        //创建触发器
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();

        Set<Trigger> triggerSet = new HashSet<>();
        triggerSet.add( getTrigger1(2,"a","A"));
        triggerSet.add( getTrigger1(4,"b","B"));

        //创建调度器，粘合工作和触发器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail,triggerSet,false);

        //启动调度器
        System.out.println("开始  " + new Date());
        scheduler.start();
    }

    private static Trigger getTrigger(SimpleScheduleBuilder simpleScheduleBuilder,int delay) {
        return TriggerBuilder.newTrigger()
                    .withDescription("触发器的描述")
                    .withIdentity("a", "a")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                    .startAt(DateUtils.addSeconds(new Date(), delay))
                    .build();
    }

    private static SimpleTrigger getTrigger1(int delay,String name,String group) {
        return  TriggerBuilder.newTrigger()
                .withIdentity(name, "a")
                .startAt(DateUtils.addSeconds(new Date(), delay))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();
    }
}
