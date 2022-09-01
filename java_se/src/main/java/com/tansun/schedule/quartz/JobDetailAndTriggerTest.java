package com.tansun.schedule.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class JobDetailAndTriggerTest {
    private static Scheduler scheduler = null;

    public static void main(String[] args) throws InterruptedException {
        addJobDetailAndTrigger();
        Thread.sleep(9000);// 让主线程休眠9秒
        deleteJobDetailAndTriggerTest();
    }

    /**
     * 添加定时任务
     */
    private static void addJobDetailAndTrigger() {
        // 创建 JobDetail 的实例
        JobDetail jobDetail = JobBuilder.newJob(Myjob.class)
                .withDescription("Myjob 描述")
                .withIdentity("jobDetailName","jobDetai_groupName")
                .build();

        // 向程序内部传入参数 绑定这个jobDetail的多个job实例 共享同一个 jobDetail 实例 数据
        JobDataMap jobDataMapDetail = jobDetail.getJobDataMap();
        jobDataMapDetail.put("userName","fawai狂徒张三");

        // 创建Trigger触发器 每5秒运行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withDescription("")
                // 如果为空字符串 报错Trigger name cannot be null or empty.
                .withIdentity("TriggerName","Trigger_groupName")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * *  * * ?"))
                .build();

        // 向程序内部传入参数 绑定这个cronTrigger的多个job实例 共享同一个cronTrigger实例的数据
        JobDataMap jobDataMapTrigger = cronTrigger.getJobDataMap();
        jobDataMapTrigger.put("time","触发时间");

        try {
            // 创建schedule实例
            scheduler = new StdSchedulerFactory().getScheduler();

            // 绑定job任务与 trigger触发器
            scheduler.scheduleJob(jobDetail, cronTrigger);

            // 启动 任务调度
            scheduler.start();

            // 停止定时任务
//		    scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除定时任务
     */
    private static void deleteJobDetailAndTriggerTest(){
        System.out.println("********开始移除定时任务*********");
        TriggerKey triggerKey = new TriggerKey("TriggerName", "Trigger_groupName");
        JobKey jobKey = new JobKey("jobDetailName", "jobDetai_groupName");
        try {
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(jobKey);// 删除任务
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
