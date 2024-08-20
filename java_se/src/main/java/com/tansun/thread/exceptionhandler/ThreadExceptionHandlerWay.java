package com.tansun.thread.exceptionhandler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.tansun.thread.pool.Task.CallTask;

import java.util.concurrent.*;


public class ThreadExceptionHandlerWay {

    private static ExecutorService executor = Executors.newFixedThreadPool(2);


    public static void main(String[] args) {
        exceptionHandlerWay1();

    }

    /**
     * 1.在提交的任务中捕获异常 在提交到线程池的任务中，你可以捕获异常并进行适当的处理。
     * 例如，你可以记录错误信息，重试操作，或者采取其他补救措施。这种方式适用于你能够预见并处理的异常
     */
    private static void exceptionHandlerWay1() {
        executor.submit(() -> {
            try {
                // 执行可能抛出异常的操作
            } catch (Exception e) {
                // 异常处理逻辑
                System.err.println("Caught exception: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    /**
     * 2.使用 Future 获取异常
     * 如果你使用 ExecutorService 的 submit 方法提交了一个 Callable 任务，可以通过 Future.get()
     * 方法获取任务的结果，同时也能捕获到任务抛出的异常。这样可以让你在主线程中处理异常。
     */
    private static void exceptionHandlerWay2() {
        Future<?> future = executor.submit(new CallTask());
        try {
            future.get(); // 这里会抛出任务中抛出的异常
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            // 处理异常
        } catch (InterruptedException e) {
            // 处理中断异常
        }
    }

    /**
     * 3.设置未捕获异常处理器
     * 你可以为线程池中的线程设置一个未捕获异常处理器。这可以通过创建线程时使用 ThreadFactory 来实现。
     * 未捕获异常处理器会在线程结束时被调用，提供了一个机会来处理异常。
     */
    private static void exceptionHandlerWay3() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setUncaughtExceptionHandler((thread, throwable) -> {
            // 异常处理逻辑
            System.err.println("Caught exception in thread " + thread.getName() + ": " + throwable.getMessage());
            throwable.printStackTrace();
        }).build();
        ExecutorService executor = Executors.newFixedThreadPool(10, threadFactory);
    }

    /**
     * 4.监听线程池的异常
     * 从 Java 9 开始，ExecutorService 接口添加了
     * execute(Runnable command, CompletableFuture<Void> result) 方法，
     * 可以使用 CompletableFuture 来监听异常。
     */
    private static void exceptionHandlerWay4() {
        CompletableFuture<Void> future = new CompletableFuture<>();
        executor.execute(() -> {
            try {
                // 执行任务
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });

        future.whenComplete((result, throwable) -> {
            if (throwable != null) {
                // 处理异常
            }
        });
    }

    /**
     * 5.自定义 RejectedExecutionHandler
     * 当线程池无法接受更多任务时，你可以自定义一个拒绝策略处理器（RejectedExecutionHandler），
     * 并在其中处理异常。虽然这不是直接处理线程异常，但它可以帮助你了解线程池的饱和状态。
     */
    private static void exceptionHandlerWay5() {
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // 处理拒绝策略
                System.err.println("Rejected execution: " + r);
            }
        };

        // 或者使用 lambda 表达式
        RejectedExecutionHandler handler = (r, executor) -> {
            // 处理拒绝执行的情况，例如记录日志或抛出异常
        };
        ExecutorService executor = new ThreadPoolExecutor(2, // corePoolSize核心线程数
                3, // maximumPoolSize 最大线程数
                2, // keepAliveTime 空闲时间
                TimeUnit.SECONDS, // unit 时间单位
                new ArrayBlockingQueue<>(10),// workQueue 任务队列
                rejectedExecutionHandler);

    }
}
