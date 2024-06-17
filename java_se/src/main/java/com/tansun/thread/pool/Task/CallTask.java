package com.tansun.thread.pool.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Callable<Object>定义了Callable的返回值类型
 *      call()方法返回的对象为该对象 或其子类
 */
public class CallTask implements Callable<Object> {

    private int  i = 0;
    private Logger logger = LoggerFactory.getLogger(CallTask.class);

    public CallTask(){}



    @Override
    public String call() throws Exception {
        logger.info("{}线程会暂停5秒后，开始执行CallTask任务",Thread.currentThread().getName());
        if ("pool-1-thread-2".equals(Thread.currentThread().getName())){
            Thread.currentThread().sleep(2000);
        }
        if ("pool-1-thread-1".equals(Thread.currentThread().getName())){
            Thread.currentThread().sleep(4000);
        }
        Thread.currentThread().sleep(4000);
        logger.info("{}线程，执行了CallTask任务，该任务第{}次执行",Thread.currentThread().getName(), ++i);
        return Thread.currentThread().getName();
    }
}
