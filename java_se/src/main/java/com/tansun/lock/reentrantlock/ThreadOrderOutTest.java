package com.tansun.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOrderOutTest {

    static Thread t1, t2, t3;

    public static void main(String[] args) {
//        awiateAndSingal();

        parkAndUnpark();
    }

    /**
     * 让线程交替执行，并且按固定循序执行
     * A->B->C...A->B->C...A->B->C
     */
    private static void parkAndUnpark() {
        ParkAndUnpark task = new ParkAndUnpark(4);

        t1 = new Thread(()->{
            task.pritTask("a", t2);
        },"线程A");

        t2 = new Thread(()->{
            task.pritTask("b", t3);
        },"线程B");

        t3 = new Thread(()->{
            task.pritTask("c", t1);
        },"线程C");
        t1.start();
        t2.start();
        t3.start();
        // 唤醒线程A
        LockSupport.unpark(t1);
    }

    /**
     * 让线程交替执行，并且按固定循序执行
     * A->B->C...A->B->C...A->B->C
     */
    private static void awiateAndSingal() {
        AwiateAndSingal reentrantLockChild = new AwiateAndSingal(4);
        Condition condition_a = reentrantLockChild.newCondition();
        Condition condition_b = reentrantLockChild.newCondition();
        Condition condition_c = reentrantLockChild.newCondition();

        // 分别启动三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockChild.printTask("a",condition_a, condition_b);
            }
        },"线程A").start();

        new Thread(()->{
            reentrantLockChild.printTask("b", condition_b, condition_c);
        }, "线程B").start();

        new Thread(()->{
            reentrantLockChild.printTask("c", condition_c, condition_a);
        }, "线程C").start();

        /**
         * await()与signal()方法，均需要用在lock()与 unlock()之间，否则会报错
         */
        try {
            reentrantLockChild.lock();
            // main线程唤醒 A线程
            condition_a.signal();
        } finally {
            reentrantLockChild.unlock();
        }
    }
}
