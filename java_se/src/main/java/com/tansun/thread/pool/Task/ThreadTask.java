package com.tansun.thread.pool.Task;

public class ThreadTask extends Thread {

    @Override
    public void run() {

        System.out.println("重写Thread类的run方法");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
