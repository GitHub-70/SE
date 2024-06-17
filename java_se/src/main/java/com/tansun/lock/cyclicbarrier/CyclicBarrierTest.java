package com.tansun.lock.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier---"可重复使用的栅栏--循环栅栏”
 * CyclicBarrier是Java中的一个同步辅助类，它允许一组线程相互等待，直到所有线程都到达一个共同的屏障点。
 * 它是可重用的，一旦所有线程都到达屏障点，它将被重置以便下次使用。
 * 它可以接受一个可选的Runnable任务，当所有线程到达屏障点时，最后一个到达的线程将运行该任务。
 * 它可以指定等待的线程数量，当指定数量的线程都到达屏障点时，屏障将打开
 *
 * 举个例子，比如小明，小美，小华，小丽几人终于历经多年课本出题历程，高考结束，相约一起聚餐，
 * 然而他们每个人到达约会地点的耗时可能都不一样，有的人会早到，有的人会晚到，但是他们要都到了以后才可以决定点那些菜。
 *      缺点：使用CyclicBarrier的缺点在于，需要明确知道总共有多少个阶段，同时并行的任务数需要提前预定义好，
 *      且无法动态修改。 而Phaser可同时解决这两个问题
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("最后一个"+Thread.currentThread().getName()+"同学已到达");
                System.out.println("最后一个到达同学的额外任务：号召所有同学开始点餐！");
            }
        });

        // 这个阶段的任务数
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Worker(cyclicBarrier));
            thread.start();
        }

    }


}

/**
 * 工作线程
 */
class Worker implements Runnable {

    final CyclicBarrier barrier;

    public Worker(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " is waiting at the barrier，已到达餐厅！");
            barrier.await();
            System.out.println("Thread " + Thread.currentThread().getName() + " has passed the barrier，开始点餐！");

            // BrokenBarrierException 表示栅栏已经被破坏，破坏的原因可能是其中一个线程 await() 时被中断或者超时
            // 可以看到不管是定时等待还是非定时等待，它们都调用了dowait方法
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
