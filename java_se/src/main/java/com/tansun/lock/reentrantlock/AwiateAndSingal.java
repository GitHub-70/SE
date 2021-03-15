package com.tansun.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 该类为了创建多个Condition变量
 */

public class AwiateAndSingal extends ReentrantLock {

//    private static ReentrantLock reentrantLock = new ReentrantLock();// 默认是非公平锁

    // 线程循环次数
    private int loopNumber;

    public AwiateAndSingal(int loopNumber){
        this.loopNumber = loopNumber;
    }

    /**
     * 让创建的线程依次循环输出
     * @param msg
     * @param currentThread
     * @param nextThread
     */
    public void printTask(String msg, Condition currentThread, Condition nextThread){
        for (int i = 0; i < loopNumber; i++) {
            //让当前线程等待，当当前线程被唤醒时，输出一句话，并唤醒下一个线程
            /** await()与signal()方法，均需要用在lock()与 unlock()之间，否则会报错*/
            super.lock();
            try {
                //让当前线程等待
                currentThread.await();
                //当当前线程被唤醒时，输出一句话，并唤醒下一个线程
                System.out.println(Thread.currentThread().getName()+"被唤醒，输出内容为："+msg);
                nextThread.signal();
                // 让线程C 执行完后睡一秒
                if (Thread.currentThread().getName().equals("线程C")){
                    System.out.println("---------------第"+(i+1)+"轮输出结束----------------");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                unlock();
            }
        }
    }
}
