package com.tansun.lock.reentrantlock;

import java.util.concurrent.locks.LockSupport;

/**
 * sun.misc.Unsafe
 *      --park()
 *      --unpark()
 *
 * ReentrantLock 的核心方法
 *   1.lock() 和 unlock()：
 *      lock() 方法通过调用 AQS 的 acquire() 方法尝试获取锁。
 *      unlock() 方法通过调用 AQS 的 release() 方法释放锁。
 *   2.tryAcquire() 和 tryRelease()：
 *      tryAcquire() 方法尝试获取锁，返回 true 表示成功，否则返回 false。
 *      tryRelease() 方法尝试释放锁，返回 true 表示成功，否则返回 false。
 *   3.Condition：
 *      ReentrantLock 还支持多个条件变量（Condition），允许更细粒度的线程间同步。
 *
 * 实现细节
 *   1.内部类 Sync：
 *      ReentrantLock 定义了一个内部类 Sync 继承自 AQS。
 *      Sync 类有两个子类：NonfairSync 和 FairSync，分别实现非公平锁和公平锁。
 *   2.tryAcquire()：
 *      在 NonfairSync 中，tryAcquire() 方法会尝试立即获取锁。
 *      在 FairSync 中，tryAcquire() 方法会检查队列头部是否有等待的线程，如果有则等待，保证公平性。
 *   3.tryRelease()：
 *      tryRelease() 方法用于释放锁，减少 state 的值。
 *   4.Condition：
 *      ReentrantLock 提供了 newCondition() 方法创建一个新的 Condition 对象。
 *      Condition 对象允许线程等待和唤醒，通常与 lock() 和 unlock() 结合使用。
 */
public class ParkAndUnpark {

    private int loopNumber;

    public ParkAndUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void pritTask(String msg, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            // 暂停当前线程，（每个线程进来前，都给锁住--只有拿到钥匙后，才能执行）
            LockSupport.park();
            // 当前线程被唤醒时，输出内容
            System.out.println(Thread.currentThread().getName() + "被唤醒，输出内容为：" + msg);

            if (("线程C").equals(Thread.currentThread().getName())) {
                System.out.println("---------------第" + (i + 1) + "轮输出结束----------------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 唤醒下一个线程（下一个线程拿到万能钥匙）
            LockSupport.unpark(next);
        }
    }
}
