package com.tansun.schedule.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Myjob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss SSS");
        String date = simpleDateFormat.format(new Date());

        // 获取外部传来的参数
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMapDetail = jobDetail.getJobDataMap();
        String userName = jobDataMapDetail.getString("userName");

        // 获取外部传来的参数
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDataMap jobDataMapTrigger = trigger.getJobDataMap();
        Object time = jobDataMapTrigger.get("time");

        Job jobInstance = jobExecutionContext.getJobInstance();

        System.out.println("jobDataMapDetail.getString(\"userName\"):"+userName);
        System.out.println("jobDataMapTrigger.get(\"time\"):"+time);
        System.out.println("当前时间："+date);
    }
}
