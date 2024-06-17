package com.tansun.thread;


import com.tansun.thread.pool.Task.CallTask;
import com.tansun.thread.task.RunableTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName MoreThread
 * @date 2020/11/1 22:17
 * @Copyright © 2020 阿里巴巴
 *
 * 更多细节：https://juejin.cn/post/6970558076642394142
 */
public class MoreThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        execute();

        callable();

    }

    private static void execute() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new RunableTask());
        }
        // CPU 核数
        int cpuCoreNum = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU核数：" + cpuCoreNum);
    }

    private static Future callable() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future future = null;
        // 任务执行剩余次数与CountDownLatch对应
        int tastNum = 6;

        for (int i = 0; i < 6; i++) {
            future = executorService.submit(new CallTask());
            try {
                System.out.println("当前线程是否执行完任务：" + future.isDone());
                String num = (String) future.get();
                System.out.println("future.get()-->" + num);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 让线程池处于shutdown状态，此时线程池不在接受新的任务，但能处理已添加的任务
        executorService.shutdown();
        return future;
    }
}
