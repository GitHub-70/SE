package com.tansun.Thread;

import com.tansun.Thread.Task.RunableTask;
import com.tansun.pool.Task.CallTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName MoreThread
 * @date 2020/11/1 22:17
 * @Copyright © 2020 阿里巴巴
 */
public class MoreThread  {

    public static void main(String[] args) {
        execute();
//        callable();
//        ExecutorService executorService1 = Executors.newCachedThreadPool();

    }
    private static void execute(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++){
            executorService.execute(new RunableTask());
        }
        // CPU 核数
        int cpuCoreNum = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU核数："+cpuCoreNum);
    }

    private static Future callable(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future future = null;
        // 任务执行剩余次数与CountDownLatch对应
        int tastNum = 6;
        for(int i = 0; i < 6; i++){
            future = executorService.submit(new CallTask(tastNum));
            try {
                System.out.println("当前线程是否执行完任务："+future.isDone());
                long num = (long)future.get();
                System.out.println("future.get()-->"+ num);
                tastNum = (int) num;
                if (num == 0){
                    // 让线程池处于shutdown状态，此时线程池不在接受新的任务，但能处理已添加的任务
                    executorService.shutdown();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return future;
    }
}
