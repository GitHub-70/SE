package com.tansun.thread.interrupt;

/**
 * 中断正在运行中的线程
 */
public class InterruptExample extends Thread {
    public void run() {
        while (!isInterrupted()) {
            // 执行任务
            System.out.println("自定义线程执行任务！");

//             如果当前线程在 sleep, 被其他线程调用该线程的中断时，会抛出中断异常
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }

        System.out.println("自定义线程被中断");
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptExample thread = new InterruptExample();
        System.out.println(String.format("自定义线程未启动前，中断状态--%s",thread.isInterrupted()));
        thread.start();
        try {
            Thread.sleep(10);  // 等待1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("自定义线程再运行，中断之前，中断状态--%s",thread.isInterrupted()));
        // 由运行变为中断，第一次调用 isInterrupted() 为 True,之后再调用 isInterrupted() 均为 false;
        thread.interrupt();  // 主线程中断 自定义线程（中断状态为 TRUE）
        /**
         * 该 interrupted() 方法
         * 底层调用的是 当前线程（当前线程的中断标识会被清除，即改为false）--运行这段代码的线程即就是主线程，
         * 所以是主线程的 中断状态为false,而不是 自定义线程 的中断状态为 false
         * 下面这句代码 等同于 Thread.interrupted();
         */
//        thread.interrupted();


        for (int i = 0; i < 10; i++) {
//            if (i > 0)thread.interrupt();
            System.out.println(String.format("自定义线程中断之后，中断状态--%s",thread.isInterrupted()));

        }

    }
}

