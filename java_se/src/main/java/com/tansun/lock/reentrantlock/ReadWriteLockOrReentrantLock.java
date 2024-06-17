package com.tansun.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *     "读-读" 不互斥
 *     "读-写" 互斥
 *     "写-写" 互斥
 *
 *     锁降级：从写锁变成读锁；
 * 　　 锁升级：从读锁变成写锁。
 * 更多详细使用场景：https://blog.csdn.net/qq_20499001/article/details/103650310
 */
public class ReadWriteLockOrReentrantLock {

    /**
     * 排他锁
     */
    final static ReentrantLock reentrantLock = new ReentrantLock();
    private static int value;

    /**
     * 读写锁
     */
    final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public static void main(String[] args) {
//        Runnable readR = () -> readValue(reentrantLock);
        Runnable readR = () -> readValue(readWriteLock.readLock());

//        Runnable writeR = () -> writeValue(reentrantLock, new Random().nextInt());
        Runnable writeR = () -> writeValue(readWriteLock.writeLock(), new Random().nextInt());

        for (int i = 0; i < 20; i++){
            if (i==2||i==15){
                new Thread(writeR).start();
            } else {
                new Thread(readR).start();
            }
        }

    }

    /**
     * 读取 Value 值
     *
     * 读数据为什么需要上锁呢？ TODO
     *      避免读到脏数据，（避免读到，未写完的数据--即写的时候不能读取，只能读取写之前 或 写之后的数据）
     */
    static void readValue(Lock readLock){

        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+":read over! value:"+value);
            value = value+1;
            System.out.println(Thread.currentThread().getName()+":readWrite over! value:"+value);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 写入 value 值
     * @param writeLock
     * @param val
     */
    static void writeValue(Lock writeLock, int val){

        try {
            writeLock.lock();
            System.out.println("-------------------------------------------------");
            System.out.println(Thread.currentThread().getName()+":write before! value:" + value);
            value = val;
            System.out.println(Thread.currentThread().getName()+":write over! val:" + val);
        } finally {
            writeLock.unlock();
        }
    }


}
