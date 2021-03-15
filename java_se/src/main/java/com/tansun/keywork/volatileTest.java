package com.tansun.keywork;

public class volatileTest {
    /**
     * volatile 保证线程的可见性，防止指令重排序（JVM层级-内存屏障--Load读、Store写）
     * JVM层级-内存屏障四种实现LoadLoad、LoadStore、StoreLoad、StoreStore--在中间加入内存屏障
     * hanppens-before原则 8中情况不允许指令重排序（JVM规范）
     * as-if-serial 单线程里，无论如何指令重排序，不影响结果
     * 系统底层指令：lock addl
     * lock的作用：将当前处理器 对应的缓存内容刷新到内存，并使其他处理器 缓存内容失效，
     * 另外还提供了有序的指令 无法越过内存屏障的作用
     * MESI缓存一致性协议：多核CPU的一二三级缓存，cpu从内存中cache line大小（64字节）的数据
     * 放入缓存中，当cache line中数据发生变化时，要通知其他核 从内存中拉取数据
     */
    private static volatile boolean flag1 = true;
    private static boolean flag2 = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (flag1){}
        },"falg1线程").start();
        System.out.println("新开起的线程 读取到了flag1");

        // 主线程将flag1设置为false 看新线程是否能读取到
        flag1 = false;
        // 主线程将flag2设置为false 看新线程是否能读取到
        flag2 = false;

        new Thread(() -> {
            try {
                // 保证falg1线程 先执行
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (flag2){
                System.out.println("新开起的线程 读取到了flag2");
            }
            System.out.println("新开起的线程 未能读取到了flag2");
        },"falg2线程").start();
    }
}
