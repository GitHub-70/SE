package com.tansun.lock.reentrantlock.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AQS:AbstractQueuedSynchronizer
 *
 * 用到AQS:
 *      ReentrantLock
 *      CountDownLatch
 *      ReentrantReadWriteLock
 *      Semaphore
 *
 * AQS源码分析：     双向链表队列
 *      private transient volatile Node head;
 *      private transient volatile Node tail;
 *      private volatile int state;
 *      static final class Node {
 *          Node SHARED = new Node();
 *          Node EXCLUSIVE = null;
 *          int CANCELLED =  1;
 *          int SIGNAL    = -1;
 *          volatile int waitStatus;
 *          volatile Node prev;     // 头部
 *          volatile Node next;     // 尾部
 *          volatile thread thread;
 *          Node nextWaiter;
 *      }
 *
 *      addWaiter(Node.EXCLUSIVE)：进入队列，初始化头、尾node
 *      完全初始化的Node为t,
 */
public class SyncImpl extends AbstractQueuedSynchronizer {

    /**
     * 该方法为钩子方法，父类空实现
     * 子类去实现：尝试去加锁
     * @param arg
     * @return
     */
    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);

    }

    /**
     * 该方法为钩子方法，父类空实现
     * 子类去实现：尝试去释放锁
     * @param arg
     * @return
     */
    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }
}
