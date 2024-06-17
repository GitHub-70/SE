package com.tansun.thread.pool;

import java.util.concurrent.*;

public class CustomThreadPool {


    public static void main(String[] args) {

        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(5); // 有界队列
        LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<>(); // 无界队列


        // 创建一个自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // 核心线程数
                5, // 最大线程数
                60, // 空闲线程存活时间
                TimeUnit.SECONDS, // 时间单位
                new ArrayBlockingQueue<>(5), // 任务队列
                new CustomRejectedExecutionHandler());

        // 提交任务
        for (int i = 0; i < 20; i++) {
            final int taskId = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("正在处理任务：" + taskId);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
    }

    /**
     * 自定义拒绝策略
     */
    static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                // 当任务队列满了之后的处理机制，这里采取丢弃最旧的任务的策略
                BlockingQueue<Runnable> queue = executor.getQueue();
                if (!queue.offer(r, 0, TimeUnit.SECONDS)) {
                    System.out.println("任务队列已满，丢弃最旧的任务：" + queue.poll().toString());
                    queue.put(r);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

