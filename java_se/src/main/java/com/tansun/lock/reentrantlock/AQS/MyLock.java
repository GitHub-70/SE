package com.tansun.lock.reentrantlock.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 用到AQS:
 *      ReentrantLock
 *      CountDownLatch
 *      ReentrantReadWriteLock
 *      Semaphore
 *
 *  加锁与释放锁的操作 均需具备原子性
 *  加锁与释放锁的操作 均需同一线程
 */
public class MyLock implements Lock {

//    private volatile Integer state;
    private Sync sync = new Sync();

    @Override
    public void lock() {
        /**
         * 模板方法模式
         * 调用AQS的acquire()方法,底层调用tryAcquire()方法时，
         * 进入自己的实现tryAcquire()方法
         */
        sync.acquire(1);
    }

    // 中断
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // 调用AQS的release()方法，底层调用tryRelease()方法时，进入自己的实现tryRelease()方法
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private class Sync extends AbstractQueuedSynchronizer {

        /**
         * 需要自己现实tryAcquire、tryRelease方法
         * @param arg
         * @return
         */

        // 加锁
        @Override
        protected boolean tryAcquire(int arg) {
            // 断言(明确肯定) 当arg是1时，就正常通过，否则就报程序错误err
            assert arg == 1;
            // 如果自旋成功，设置互斥锁 被当前线程独占,并且返回true,否则就返回false
            if (compareAndSetState(0, 1)){
                // 设置互斥锁的拥有者 为当前线程
                // 此操作的目的是 加锁与释放锁 要为同一线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁
        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            // 如果互斥锁的拥有者不是当前线程，抛出MonitorStateException
            if (!isHeldExclusively()) throw new IllegalMonitorStateException();
            // 否则将锁的拥有者 置为null，状态置为0，并返回true
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            // 判断获取到的是不是当前线程
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }
}
