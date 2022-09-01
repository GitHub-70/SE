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
            // 暂停当前线程，（每个线程进来前，都给锁住--只有拿到钥匙后，才能执行）
            LockSupport.park();
            // 当前线程被唤醒时，输出内容
            System.out.println(Thread.currentThread().getName() + "被唤醒，输出内容为：" + msg);

            if (("线程C").equals(Thread.currentThread().getName())) {
                System.out.println("---------------第" + (i + 1) + "轮输出结束----------------");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 唤醒下一个线程（下一个线程拿到万能钥匙）
            LockSupport.unpark(next);
        }
    }
}
