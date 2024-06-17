package com.tansun.keywork;

import java.util.concurrent.CountDownLatch;

/**
 * 解决cache line伪共享问题
 * Disruptor
 * 同一个代码，不同的java版本， 性能相差明显
 */
public class RingBufferTest01 {

    // 一亿次
    public static Long count = 100000000l;

    private static class  T {
        private volatile long x = 0l;// 8字节
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
