package com.tansun.schedule;

import com.tansun.Thread.Task.RunableTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsTimingTask {

    private static ScheduledExecutorService scheduledExecutorService = null;


    public static void main(String[] args) {
        getSingleThreadScheduledExecutor();
//        getScheduledExecutorService();
    }

    private static ScheduledExecutorService getSingleThreadScheduledExecutor(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 程序启动3秒后，开始执行任务，每隔两秒执行一次任务
        scheduledExecutorService.scheduleAtFixedRate(new RunableTask(),3,2, TimeUnit.SECONDS);

        return scheduledExecutorService;
    }

    private static ScheduledExecutorService getScheduledExecutorService(){

        scheduledExecutorService = Executors.newScheduledThreadPool(5);

        scheduledExecutorService.scheduleAtFixedRate(new RunableTask(),3,2, TimeUnit.SECONDS);
        // 程序启动3秒后，开始执行任务，每隔两秒执行一次任务
//        scheduledExecutorService.scheduleWithFixedDelay(new RunableTask(),3,2, TimeUnit.SECONDS);

        return scheduledExecutorService;
    }
}
