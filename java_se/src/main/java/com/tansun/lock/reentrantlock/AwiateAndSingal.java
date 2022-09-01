package com.tansun.lock.reentrantlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 该类为了创建多个Condition变量
 */

public class AwiateAndSingal extends ReentrantLock {

    private static final Logger logger = LoggerFactory.getLogger(AwiateAndSingal.class);

//    private static ReentrantLock reentrantLock = new ReentrantLock();// 默认是非公平锁

    // 用于判断该线程是否 获取锁
    private volatile boolean condition_a_isGetLock = false;
    private volatile boolean condition_b_isGetLock = false;
    private volatile boolean condition_c_isGetLock = false;
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
            /**
             * Condition的await()与signal()方法，均需要用在lock()与 unlock()之间，否则会报错
             *          由此可判断：当当前线程在 唤醒下一个线程 时，下一个线程被唤醒之前
             *          未能获取CPU的执行时间执行super.lock()，也就是该对象未能 获取到 lock（），
             *          所以当前线程的在调 下一个线程Condition对象的signal()方法时，是无效的，
             *          一旦下一个线程 获取到CPU的执行时间，进来后就一直会陷入等待
             *
             *  解决方案：在当前线程在唤醒 下一个线程的Condition前，先判断 下一个线程的Condition
             *           对象是否获取到锁，获取到则唤醒
             */

            super.lock();
            updateConditionIsGetLock();
            String currentThreadName = Thread.currentThread().getName();
            try {
                //让当前线程等待
                logger.info("[{}]进入,进入之后被等待", currentThreadName);
                currentThread.await();

                //当当前线程被唤醒时，输出一句话，并唤醒下一个线程
                logger.info("当前线程为:[{}],唤醒下一个线程", currentThreadName);
//                nextThread.signal();

                // 判断下一个线程是否获取到锁，直到下一个线程获取到锁为止
                while (true){
                    // 判断下一个线程是否获取到锁,获取到锁，就唤醒并跳出循环
                    if (isGetLock()){
                        nextThread.signal();
                        break;
                    // 当最后一轮线程A,在跳出循环的同时，释放锁，所以线程C会进入死循环，需增加此判断条件
//                    } else if(i+1 == loopNumber && ("线程C").equals(currentThreadName)){
//                        break;
                    } else {
                        // 让当前线程睡 100毫秒
                        currentThread.awaitNanos(100);
                    }
                }

                logger.info("[{}]被唤醒，输出内容为：[{}]", currentThreadName, msg);
                // 让线程C 执行完后睡一秒
                if (currentThreadName.equals("线程C")){
                    logger.info("---------------第{}轮输出结束----------------", (i+1));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 修改锁的状态，并释放锁
                updateConditionNoGetLock();
                unlock();
            }
        }
    }

    /**
     * 当前线程调用此方法时，说明该线程 已经获取锁
     */
    private void updateConditionIsGetLock(){
        if (("线程A").equals(Thread.currentThread().getName())){
            condition_a_isGetLock = true;
            return;
        }
        if (("线程B").equals(Thread.currentThread().getName())){
            condition_b_isGetLock = true;
            return;
        }
        if (("线程C").equals(Thread.currentThread().getName())){
            condition_c_isGetLock = true;
            return;
        }
    }


    /**
     * 当前线程调用此方法时，说明该线程 准备释放锁
     */
    private void updateConditionNoGetLock(){
        if (("线程A").equals(Thread.currentThread().getName())){
            condition_a_isGetLock = false;
            return;
        }
        if (("线程B").equals(Thread.currentThread().getName())){
            condition_b_isGetLock = false;
            return;
        }
        if (("线程C").equals(Thread.currentThread().getName())){
            condition_c_isGetLock = false;
            return;
        }
    }

    /**
     * 用于判断下一个线程是否 获取到锁
     * @return
     */
    private boolean isGetLock(){
        if (("线程A").equals(Thread.currentThread().getName())){
            if (condition_b_isGetLock == false)
            return false;
            return true;
        }
        if (("线程B").equals(Thread.currentThread().getName())){
            if (condition_c_isGetLock == false)
                return false;
                return true;
        }
        if (("线程C").equals(Thread.currentThread().getName())){
            if (condition_a_isGetLock == false)
                return false;
                return true;
        }
        return false;
    }
}
