package com.tansun.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MergeTaskThread {

    private static Logger logger = LoggerFactory.getLogger(MergeTaskThread.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        test1();

        test2();
    }

    /**
     * CompletableFuture 等待异步完成的方式
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 异步执行任务，返回结果
            try {
                String threadName = Thread.currentThread().getName();
                logger.info("Thread Name: " + threadName);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });

        /**
         * 方式一 等待异步任务完成
         *      CompletableFuture.allOf 返回一个新的 CompletableFuture，
         *      当所有给定的 CompletableFuture 完成时，将返回一个新的 CompletableFuture
         */
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future);
        logger.info("voidCompletableFuture 完成状态: " + voidCompletableFuture);
        voidCompletableFuture.get();
        logger.info("阻塞方法get()后 voidCompletableFuture的完成状态: " + voidCompletableFuture);

        /**
         * 方式二
         * future.get()和future.join()都是用来获取CompletableFuture结果的方法，但它们处理异常的方式不同：
         * future.get(): 这个方法会阻塞直到结果可用。如果异步任务在完成时抛出了异常，
         *      get()会抛出一个ExecutionException，并且这个异常的cause是原始的异常。
         *      这意味着你需要捕获ExecutionException并检查它的cause来处理原始的异常。
         *      此外，如果调用get()时任务已经被取消，它会抛出CancellationException。
         * future.join(): 与get()类似，join()也会阻塞直到结果可用。
         *      但它抛出的是未检查的异常（即RuntimeException或其子类），通常是CompletionException，
         *      该异常的cause是异步计算中抛出的任何异常。这意味着join()更适合那些期望在没有显式异常处理
         *      的情况下使用的代码，因为未检查异常不需要在方法签名中声明。
         *
         * 总结来说，join()通常提供一个更简洁的API，适合链式调用和函数式编程风格，而get()则保留了
         * 传统异常处理的语义，需要显式的异常捕获。
         */
//        future.join(); // 或者使用 future.get();

        future.thenAccept(result -> {
            // 处理异步任务的结果
            logger.info("Result: " + result);
        });
    }

    /**
     * 两个不同耗时的任务，都需要等待完成
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        });

        // 等待异步任务完成
//        CompletableFuture.allOf(future1).get();

        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + result2);

        // 等待两个异步任务完成
        CompletableFuture.allOf(future1, future2).get();

        combinedFuture.thenAccept(result -> {
            logger.info("Combined Result: " + result);
        });
    }

//    public static void test3() throws ExecutionException, InterruptedException {
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            // 模拟耗时操作
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 10;
//        });
//
//        CompletableFuture<Integer> timeoutFuture = future.orTimeout(3, TimeUnit.SECONDS);
//        timeoutFuture.exceptionally(ex -> {
//            // 处理超时异常
//            System.out.println("Timeout occurred: " + ex.getMessage());
//            return 0; // 返回默认值
//        });
//    }


}
