package com.tansun.lock.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * semaphore 常用于限流
 * 信号量有两种类型：
 *      二进制信号量（binary semaphore）：也称为互斥锁（mutex），只有两个状态，0和1。
 *      它用于保护对共享资源的互斥访问，只允许一个线程访问该资源。当线程获得互斥锁时，信号量的值为1；
 *      当线程释放互斥锁时，信号量的值为0。
 *      计数信号量（counting semaphore）：也称为通用信号量，它可以有一个正整数值。
 *      它用于控制对一组资源的访问，可以允许多个线程同时访问该资源，但要限制同时访问的线程数量。
 *
 * 使用场景：
 *      通常用于那些资源有明确访问数量限制的场景，常用于限流 。
 *      例如1：数据库连接池，同时进行连接的线程有数量限制，连接不能超过一定的数量，当连接达到了限制数量后，
 *      后面的线程只能排队等前面的线程释放了数据库连接才能获得数据库连接。
 *      例如2：停车场场景，车位数量有限，同时只能容纳多少台车，
 *      车位满了之后只有等里面的车离开停车场外面的车才可以进入。
 *
 * acquire()
 * 获取一个令牌，在获取到令牌、或者被其他线程调用中断 之前线程一直处于阻塞状态。
 *
 * acquire(int permits)
 * 获取一个令牌，在获取到令牌、或者被其他线程调用中断、或超时之前线程一直处于阻塞状态。
 *
 * acquireUninterruptibly()
 * 获取一个令牌，在获取到令牌之前线程一直处于阻塞状态（忽略中断-即忽略被其他线程唤醒）。
 *
 * tryAcquire()
 * 尝试获得令牌，返回获取令牌成功或失败，不阻塞线程。tryAcquire()方法不支持公平性设置。
 *
 * tryAcquire(long timeout, TimeUnit unit)
 * 尝试获得令牌，在超时时间内循环尝试获取，直到尝试获取成功或超时返回，不阻塞线程。
 *
 * release()
 * 释放一个令牌，唤醒 一个获取令牌不成功的阻塞线程。
 *
 * hasQueuedThreads()
 * 等待队列里是否还存在等待线程。
 *
 * getQueueLength()
 * 获取等待队列里阻塞的线程数。
 *
 * drainPermits()
 * 清空令牌把可用令牌数置为0，返回清空令牌的数量。
 *
 * availablePermits()
 * 返回可用的令牌数量。
 *
 *
 * 更多细节：https://zhuanlan.zhihu.com/p/98593407
 *         https://blog.csdn.net/J080624/article/details/85625350 重要！ 重要！ 重要！
 */
public class SemaphoreTest {

    /**
     * true 为公平模式-FIFO(先进先出)，
     * false为非公平模式
     */
    //停车场同时容纳的车辆10
    private  static  Semaphore semaphore=new Semaphore(10, true);

    public static void main(String[] args) {
        //模拟20辆车进入停车场
        for(int i=0;i<20;i++){
            Thread thread=new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("===="+Thread.currentThread().getName()+"来到停车场");
                        if(semaphore.availablePermits()==0){
                            System.out.println("车位不足，请耐心等待");
                            int queueLength = semaphore.getQueueLength();
                            System.out.println("等待队列中，排队的队列长度："+queueLength);
                        }
                        semaphore.acquire();//获取令牌尝试进入停车场
                        System.out.println(Thread.currentThread().getName()+"成功进入停车场");
                        Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                        System.out.println(Thread.currentThread().getName()+"驶出停车场");
                        semaphore.release();//释放令牌，腾出停车场车位
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },i+"号车");
            thread.start();
        }

    }
}
