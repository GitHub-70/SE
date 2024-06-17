package com.tansun.lock.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程间通信
 * V exchange(V v, long timeout, TimeUnit unit)：等待另一个线程到达此交换点，
 * 或者当前线程被中断——抛出中断异常；又或者是等候超时——抛出超时异常，然后将给定的对象传送给该线程，
 * 并接收该线程的对象
 *
 * 使用场景：
 *      线程间数据交换：当两个线程需要交换数据时，可以使用Exchanger来实现。一个线程将数据传递给Exchanger，
 * 然后等待另一个线程将数据交换回来。这种场景可以用于生产者-消费者模型（MQ）或者任务分配模型。
 *
 *      线程间数据校验：当一个线程需要等待另一个线程对数据进行校验时，可以使用Exchanger来实现。
 * 一个线程将数据传递给Exchanger，并等待另一个线程对数据进行校验。如果校验通过，
 * 另一个线程将数据交换回来，否则返回错误信息。
 *
 *      线程间数据合并：当两个线程需要将各自的数据合并成一个结果时，可以使用Exchanger来实现。
 * 一个线程将数据传递给Exchanger，并等待另一个线程将自己的数据传递过来。
 * 然后两个线程可以将数据合并成一个结果。
 */
public class ExchangerTest {

    private static final Exchanger<String> exchanger = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "12379871924sfkhfksdhfks";
                    // 先到达的线程会在此等待，直到有一个线程跟它交换数据或者等待超时（若有超时时间）
                    exchanger.exchange(A);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "32423423jknjkfsbfj";
                    String A = exchanger.exchange("B");
                    System.out.println("A和B数据是否一致：" + A.equals(B));
                    System.out.println("A= "+A);
                    System.out.println("B= "+B);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.shutdown();

    }
}
