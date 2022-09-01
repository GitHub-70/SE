package com.tansun.schedule;

import com.tansun.thread.pool.MyDefinedThreadPoolExecutor;
import com.tansun.thread.pool.Task.CallTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class SpringTimingTask {

    @Scheduled(cron = "0/5 * *  * * ? ") // 每5秒执行一次
    public static void doTask(){
        /* 开启一个异步线程执行任务 **/
        // 从线程池获取线程     TODO
        ThreadPoolExecutor threadPoolExecutor = MyDefinedThreadPoolExecutor.getThreadPoolExecutor();
        Future num = threadPoolExecutor.submit(new CallTask());
    }
}
