package com.tansun.lock.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 让线程循序执行
 * 利用Jprofile工具运行该测试，分析存在的问题
 * https://blog.csdn.net/vicky_pyh/article/details/88797514
 * 整合远程服务：
 * https://blog.csdn.net/wytocsdn/article/details/79258247?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~default-1.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~default-1.no_search_link
 */
public class ThreadOrderOutTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadOrderOutTest.class);

    static Thread t1, t2, t3;

    public static void main(String[] args) {
        // 该写法为什么会偶尔出现，未正常输出的情况？ 详见：printTask 方法
        awiateAndSingal();

//        parkAndUnpark();

    }

    /**
     * 让线程交替执行，并且按固定循序执行
     * A->B->C...A->B->C...A->B->C
     */
    private static void parkAndUnpark() {
        ParkAndUnpark task = new ParkAndUnpark(4);

        t1 = new Thread(() -> {
            task.pritTask("a", t2);
        }, "线程A");

        t2 = new Thread(() -> {
            task.pritTask("b", t3);
        }, "线程B");

        t3 = new Thread(() -> {
            task.pritTask("c", t1);
        }, "线程C");
        t1.start();
        t2.start();
        t3.start();
        // 唤醒线程A（A线程拿到万能钥匙）
        LockSupport.unpark(t1);
    }

    /**
     * 让线程交替执行，并且按固定循序执行
     * A->B->C...A->B->C...A->B->C
     */
    private static void awiateAndSingal() {
        // 创建 AwiateAndSingal 对象，设置循环次数为4次
        AwiateAndSingal reentrantLockChild = new AwiateAndSingal(4);
        Condition condition_a = reentrantLockChild.newCondition();
        Condition condition_b = reentrantLockChild.newCondition();
        Condition condition_c = reentrantLockChild.newCondition();

        // 分别启动三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockChild.printTask("a", condition_a, condition_b);
            }
        }, "线程A").start();
        // lamda写法
        new Thread(() -> {
            reentrantLockChild.printTask("b", condition_b, condition_c);
        }, "线程B").start();

        new Thread(() -> {
            reentrantLockChild.printTask("c", condition_c, condition_a);
        }, "线程C").start();

        /**
         * await()与signal()方法，均需要用在lock()与 unlock()之间，否则会报错
         */
        try {
            reentrantLockChild.lock();
            // main线程唤醒 A线程
            logger.info("[{}]主线程唤醒 线程A", Thread.currentThread().getName());
            condition_a.signal();
        } finally {
            reentrantLockChild.unlock();
            logger.info("[{}]主线程执行释放锁，执行完毕", Thread.currentThread().getName());
        }
    }

}
