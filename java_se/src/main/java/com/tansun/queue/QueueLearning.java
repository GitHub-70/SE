package com.tansun.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ConcurrentLinkedQueue
 *      --使用CAS实现的非阻塞队列
 *
 * LinkedBlockingQueue
 *      --使用了lock实现的阻塞队列
 */
public class QueueLearning extends LinkedBlockingQueue {

    // ctrl+u 查看父类方法
    @Override
    public boolean remove(Object o) {

        return false;
    }
}
