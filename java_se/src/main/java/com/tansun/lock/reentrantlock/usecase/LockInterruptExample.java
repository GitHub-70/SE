package com.tansun.lock.reentrantlock.usecase;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 ReentrantLock 的 lock()方法 与 lockInterruptibly()方法
 */
public class LockInterruptExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void performAction() throws InterruptedException {
        /**
         * lock()方法获取锁：
         *   当调用 lock 方法时，如果锁不可用，线程将阻塞等待，直到锁可用。
         *   在这个等待过程中，如果线程被中断，线程不会立即响应中断。
         *
         * 响应中断：
         *   一旦线程成功获取锁，它会检查中断状态。
         *   如果线程在等待过程中被中断，线程会检测到中断状态，并抛出 InterruptedException。
         */
        lock.lock();
//        lock.lockInterruptibly();
//        System.out.println("isGetLock = "+ lock.tryLock(1000l, TimeUnit.MILLISECONDS));
        try {
            // 执行受保护的代码
            System.out.println("Acquired the lock.");
        } finally {
            // 如果锁正被当前线程持有，则返回 true。释放锁
            if (lock.isHeldByCurrentThread()){
                System.out.println("other thread unlock");
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockInterruptExample example = new LockInterruptExample();
        Thread thread = new Thread(() -> {
            try {
                example.performAction();
            } catch (InterruptedException e) {
                // 立即响应中断（抛出中断异常）
                System.out.println("Interrupted while holding the lock.");
            }
        });

        thread.start();
        example.lock.lock();
        try {
            System.out.println("main");
            Thread.sleep(100);// 等待线程开始执行
            thread.interrupt(); // 中断线程
            System.out.println("main interrupt end");
        } finally {
            example.lock.unlock();
            System.out.println("main finally");
        }
        thread.join(); // 等待线程结束
    }

}
