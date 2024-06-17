package com.tansun.memory;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CountDownLatch;

/**
 * 使用 VarHandle 来解决伪共享问题
 * 使用VarHandle可能会导致性能下降，这可能是因为VarHandle提供了更严格的内存顺序保证，
 * 导致写操作的开销增加。比如内存屏障操作
 */
public class FalseSharingExample {
    private static final VarHandle VAR_HANDLE;

    // 一亿次
    public static Long count = 100000000l;

    private static class  T {
        private volatile long x = 0l;// 8字节  需要避免伪共享的字段
    }

    /**
     * 准备连续的内存空间
     *   多线程读取这个小的连续的空间，共享CPU内存
     */
    private static T[] array = new T[2];  // 16 字节的连续空间

    static {
        array[0] = new T();
        array[1] = new T();
    }

    //
    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            VAR_HANDLE = lookup.findVarHandle(T.class, "x", long.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        long start = System.currentTimeMillis();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < count; i++) {
                    VAR_HANDLE.setVolatile(array[0],  i);
                }
                countDownLatch.countDown();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = 0; i < count; i++) {
                    VAR_HANDLE.setVolatile(array[1],  i);
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