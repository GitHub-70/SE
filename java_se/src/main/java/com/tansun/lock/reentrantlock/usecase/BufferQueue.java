package com.tansun.lock.reentrantlock.usecase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 假设有一个生产者-消费者模式的应用场景，其中生产者向队列中添加元素，而消费者从队列中取出元素。
 * 这里使用 ReentrantLock 和 Condition 来协调生产者和消费者的同步问题。
 *
 * 该类使用了 ReentrantLock 来保护对共享资源（队列）的访问。当队列满时，生产者调用 notFull.await() 进入等待状态；
 * 当队列空时，消费者调用 notEmpty.await() 进入等待状态。一旦队列的状态发生变化，就会通过 signal()
 * 或 signalAll() 方法唤醒等待的线程。
 * 这样，生产者和消费者就可以有效地协同工作，避免了不必要的竞争和死锁问题。
 */
public class BufferQueue {
    private final ReentrantLock lock = new ReentrantLock();
    /**
     * 队列满和空时，分别使用 notFull 和 notEmpty 条件变量进行等待和通知。
     */
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private final Object[] items = new Object[10];
    private int putIndex = 0;
    private int takeIndex = 0;
    private int count = 0;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await(); // 队列满时等待
            }
            items[putIndex] = x;
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            ++count;
            notEmpty.signal(); // 通知消费者队列不为空
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(); // 队列空时等待
            }
            Object x = items[takeIndex];
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            --count;
            notFull.signal(); // 通知生产者队列未满
            return x;
        } finally {
            lock.unlock();
        }
    }
}

