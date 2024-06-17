package com.tansun.thread;


import com.tansun.thread.pool.Task.CallTask;
import com.tansun.thread.task.RunableTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 阻塞式线程
 */
public class BioThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        callable();

    }

    private static Future callable() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future future = null;
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            futures.add(executorService.submit(new CallTask()));
        }
        System.out.println("当前futures大小：" + futures.size());
        for (Future<?> future1 : futures) {
            // 方式一：这种方式判断子线程是否执行完毕，父线程会一直消耗 cpu
//            while (true) {
//                if (future1.isDone()) {
//                    System.out.println(future1.get()+"线程做完任务！");
//                    break;
//                }
//            }

            /**
             * 方式二：该方法是阻塞方法（阻塞的方式和异步编程的设计理念相违背），所以不推荐使用
             * get()方法
             * 按照任务提交的顺序获取结果，可以查看线程输出日志
             */
            Object o = future1.get();
            System.out.println("获取任务结果：" + o);
        }
        // 让线程池处于shutdown状态，此时线程池不在接受新的任务，但能处理已添加的任务
        executorService.shutdown();
        return future;
    }
}
