package com.tansun.keywork;




import java.util.concurrent.CountDownLatch;

/**
 * 解决cache line伪共享问题
 * @sun.misc.Contended 使用此注解 必须打开参数 -XX:-RestrictContended
 *
 * 在Java 8之后，可以使用java.lang.invoke.VarHandle类提供的方法来实现更灵活的内存布局优化，
 * 而不依赖于@sun.misc.Contended注解。
 */
public class CacheLinePadding {

    // 一亿次
    public static Long count = 100000000l;

    /**
     * java8 中的 @Contended
     * https://blog.csdn.net/qq_27680317/article/details/78486220
     *
     * https://blog.csdn.net/wenyuan65/article/details/102534448
     */

    @sun.misc.Contended // 必须打开参数 -XX:-RestrictContended
    private static class  T {
        private volatile long x = 0l;// 8字节
    }

    private static  T [] array = new T[2];// 准备连续的内存空间

    static {
        array[0] = new T();// 8字节
        array[1] = new T();// 8字节
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
