package com.tansun.thread;


import com.tansun.thread.pool.Task.CallTask;
import com.tansun.thread.task.RunableTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 通过CompletionService来管理异步任务的执行结果
 *      按照线程的完成的顺序来获取任务的执行结果，非阻塞获取任务结果，优先执行完的任务，可执行下一个任务
 *
 *  原理：通过回调函数来判断线程是否执行完成
 */
public class NioTaskThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        callable();

    }


    private static Future callable() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 通过CompletionService来管理异步任务的执行结果。泛型为异步线程返回对象
        CompletionService<Object> completionService = new ExecutorCompletionService<>(executorService);

        Future future = null;

        // 方式三：非阻塞方式
        for (int i = 0; i < 3; i++) {
            completionService.submit(new CallTask());
        }
        for (int i = 0; i < 3; i++) {
            /**
             * 非阻塞方式 获取任务结果
             * completionService.take()方法
             * 用于获取已完成的任务的结果。当调用take()方法时，如果有任务已经完成，则立即返回一个Future对象，
             * 该对象包含了已完成任务的结果。如果没有任务完成，则take()方法会阻塞当前线程，直到有任务完成为止。
             */
            Future<Object> take = completionService.take();
            System.out.println("获取任务结果：" + take.get());
        }

        // 让线程池处于shutdown状态，此时线程池不在接受新的任务，但能处理已添加的任务
        executorService.shutdown();
        return future;
    }
}
