package com.tansun.keywork;

import java.util.concurrent.CountDownLatch;

/**
 * 解决cache line伪共享问题
 * 64位 cache line64个字节
 *
 */
public class RingBufferTest02 {

    // 一亿次
    public static Long count = 100000000l;

    private static class  T {
        private long p1, p2, p3, p4, p5, p6, p7;// 56字节
        private volatile long x = 0l;// 8字节

//        private long p8, p9, p10, p11, p12, p13, p14, p15; // 64字节
    }

    private static  T [] array = new T[2];// 准备连续的内存空间

    static {
        array[0] = new T();
        array[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        long start = System.currentTimeMillis();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < count; i++) {
                    array[0].x = i;
                }
                countDownLatch.countDown();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < count; i++) {
                    array[1].x = i;
                }
                countDownLatch.countDown();
            }
        }).start();

        // 等待线程执行结束
        countDownLatch.await();

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
