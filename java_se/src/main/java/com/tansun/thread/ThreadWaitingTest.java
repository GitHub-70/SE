package com.tansun.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWaitingTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadWaitingTest.class);

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition_a = reentrantLock.newCondition();
        Condition condition_b = reentrantLock.newCondition();
        Condition condition_c = reentrantLock.newCondition();
    }

    private static void printMessage(int loopNumber, String mes, Condition currentThread, Condition nextThread) throws InterruptedException {
        if (loopNumber < 0 || null == currentThread || null == nextThread){
            throw new IllegalArgumentException("parameter exception");
        }
        for (int i = 0; i < loopNumber; i++) {
            if (!("线程c").equals(Thread.currentThread().getName())){
                currentThread.await();
            }
            logger.info("{}被唤醒，输出信息为：{}", Thread.currentThread().getName(), mes);
            nextThread.signal();
        }
    }

}
