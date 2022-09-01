package com.tansun.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/weixin_38308374/article/details/113797862?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-1-113797862.pc_agg_new_rank&utm_term=java+signal详解&spm=1000.2123.3001.4430
 *
 *
 */
public interface MyCondition {


    //阻塞当前线程，等待同一个Condition对象上的signal()/signalAll()唤醒
    //该方法响应中断，如果发生中断，该方法抛出InterruptedException异常
    void await() throws InterruptedException;

    ///阻塞当前线程，等待同一个Condition对象上的signal()/signalAll()唤醒
    //与上面方法的区别是，该方法等待过程中不响应中断
    void awaitUninterruptibly();

    //阻塞线程，线程被阻塞指定的时间
    //当线程被中断、超时或者signal()/signalAll()，都会唤醒线程
    long awaitNanos(long nanosTimeout) throws InterruptedException;

    //同awaitNanos()
    boolean await(long time, TimeUnit unit) throws InterruptedException;

    //同awaitNanos()
    boolean awaitUntil(Date deadline) throws InterruptedException;

    //唤醒任意一个等待线程，注意只唤醒一个
    void signal();

    //唤醒所有的等待线程
    void signalAll();
}
