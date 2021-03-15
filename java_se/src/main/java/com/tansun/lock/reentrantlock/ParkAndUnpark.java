package com.tansun.lock.reentrantlock;

import java.util.concurrent.locks.LockSupport;

/**
 * sun.misc.Unsafe
 *      --park()
 *      --unpark()
 */
public class ParkAndUnpark {

    private int loopNumber;

    public ParkAndUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void pritTask(String msg, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            // 暂停当前线程
            LockSupport.park();
            // 当前线程被唤醒时，输出内容
            System.out.println(Thread.currentThread().getName() + "被唤醒，输出内容为：" + msg);

            if (Thread.currentThread().getName().equals("线程C")) {
                System.out.println("---------------第" + (i + 1) + "轮输出结束----------------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 唤醒下一个线程
            LockSupport.unpark(next);
        }
    }
}
