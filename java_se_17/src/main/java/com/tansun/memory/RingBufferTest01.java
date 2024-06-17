package com.tansun.memory;

import java.util.concurrent.CountDownLatch;

/**
 * cache line伪共享问题
 */
public class RingBufferTest01 {

    // 一亿次
    public static Long count = 100000000l;

    private static class  T {
        private volatile long x = 0l;// 8字节
    }

    /**
     * 准备连续的内存空间
     *   多线程读取这个小的连续的空间，共享CPU内存
     */
    private static T [] array = new T[2];  // 16 字节的连续空间

    static {
        array[0] = new T();
        array[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        long start = System.currentTimeMillis();

        /**
         * 两个线程 对同一片空间 array 两个不同区域进行赋值
         * 两个线程共享 array，产生伪共享问题，导致两线程共享CPU的3级缓存失效，又从内存中，重新读取
         */
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

        System.out.println("array[0].x = " + array[0].x);
        System.out.println("array[1].x = " + array[1].x);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
