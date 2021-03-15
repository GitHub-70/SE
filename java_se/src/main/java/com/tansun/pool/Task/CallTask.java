package com.tansun.pool.Task;

import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Callable<Object>定义了Callable的返回值类型
 *      call()方法返回的对象为该对象 或其子类
 */
public class CallTask implements Callable<Object> {

    // 执行任务次数
    private int doTaskNumber;
    private int i = 0;
    private CountDownLatch countDownLatch = null;
    private Logger logger = LoggerFactory.getLogger(CallTask.class);

    public CallTask(){}

    public CallTask(int doTaskNumber){
        this.doTaskNumber = doTaskNumber;
    }

    private CountDownLatch getCountDownLatch(int doTaskNumber){
        countDownLatch = new CountDownLatch(doTaskNumber);
        return countDownLatch;
    }

    @Override
    public Long call() throws Exception {
        CountDownLatch countDownLatch = getCountDownLatch(doTaskNumber);
        logger.info("{}线程会暂停5秒后，开始执行CallTask任务",Thread.currentThread().getName());
        Thread.currentThread().sleep(4000);
        logger.info("{}线程，执行了CallTask任务，该任务第{}次执行",Thread.currentThread().getName(), ++i);
        // 执行完任务countDownLatch递减
        countDownLatch.countDown();
        return countDownLatch.getCount();
    }
}
