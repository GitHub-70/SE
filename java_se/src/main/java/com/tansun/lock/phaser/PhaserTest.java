package com.tansun.lock.phaser;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

public class PhaserTest {
    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser();

        for (int i = 0; i < 5; i++) {
            phaser.register();
            new Thread(new DineTogetherWorker(phaser)).start();

        }
    }
}


/**
 * 团建工作线程
 */
class DineTogetherWorker implements Runnable {

    final MyPhaser phaser;

    public DineTogetherWorker(MyPhaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.println("当前线程："+Thread.currentThread().getName()+"已到达餐厅！");
        // 等待所有注册的线程，到达餐厅
        phaser.arriveAndAwaitAdvance();

        System.out.println("当前线程："+Thread.currentThread().getName()+"点了一杯杨枝甘露！");
        // 等待所有注册的线程，开始点餐
        phaser.arriveAndAwaitAdvance();

        System.out.println("当前线程："+Thread.currentThread().getName()+"点了一个鱼香肉丝");
        // 等待所有注册的线程，开始点餐
        phaser.arriveAndAwaitAdvance();

        System.out.println("当前线程："+Thread.currentThread().getName()+"已吃完饭！");
        // 等待所有注册的线程，开始点餐
        phaser.arriveAndAwaitAdvance();

        if ("Thread-0".equals(Thread.currentThread().getName())||"Thread-1".equals(Thread.currentThread().getName())){
            System.out.println("当前线程："+Thread.currentThread().getName()+"已到达游玩地点！开始游玩");
            // 等待所有注册的线程，开始点餐
            phaser.arriveAndAwaitAdvance();
        } else {

            System.out.println("当前线程："+Thread.currentThread().getName()+"已回家！");
            // 已注册的线程，取消注册。直接回家
            phaser.arriveAndDeregister();
        }

    }
}