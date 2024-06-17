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
 *          int CANCELLED =  1; // 表示当前结点已取消调度。当timeout或被中断（响应中断的情况下），会触发变更为此状态，进入该状态后的结点将不会再变化
 *          int SIGNAL    = -1; // 表示后继结点在等待当前结点唤醒。后继结点入队时，会将前继结点的状态更新为SIGNAL。
 *          int CONDITION = -2; // 表示结点等待在Condition上，当其他线程调用了Condition的signal()方法后，CONDITION状态的结点将从等待队列转移到同步队列中，等待获取同步锁。
 *          int PROPAGATE = -3; // 共享模式下，前继结点不仅会唤醒其后继结点，同时也可能会唤醒后继的后继结点。
 *          0：// 新结点入队时的默认状态。
 *          volatile int waitStatus; // 5种取值，上面四种 在加一个 0
 *          volatile Node prev;     // 头部
 *          volatile Node next;     // 尾部
 *          volatile thread thread;
 *          Node nextWaiter;
 *      }
 *
 *      注意：waitStatus 负值表示结点处于有效等待状态，而正值表示结点已被取消。
 *      所以源码中很多地方用>0、<0来判断结点的状态是否正常。
 *
 *      addWaiter(Node.EXCLUSIVE)：进入队列，初始化头、尾node
 *      完全初始化的Node为t,
 *
 *      更多详解：https://juejin.cn/post/7006895386103119908
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
        // 自实现 尝试加锁（非公平锁）
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
        // 自实现 尝试解锁（非公平锁）
        return super.tryRelease(arg);
    }
}
