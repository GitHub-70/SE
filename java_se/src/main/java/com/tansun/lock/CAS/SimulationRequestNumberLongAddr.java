package com.tansun.lock.CAS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * 模拟100个用户，每个用户访问10次，最后统计总访问量
 */
public class SimulationRequestNumberLongAddr {

    // 模拟网站总访问量
    private static Long count = 0L;
    // 用户数
    private static Integer userNum = 100;
    // 将普通变量count升级为原子变量
    /**
     * LongAdder采用分段"锁"+CAS(所谓的分段锁，就是指多个线程对某一段进行CAS)
     * 例如：十万计数，15个线程，求和为一百五十万
     * 可能分成，前五个线程，进行累加，从0加到五十万
     *          第五到十的线程，进行累加，从0加到五十万
     *          第六到十五的线程，进行累加，从0加到五十万
     *          最终这三个数求和
     *
     *     总结：LongAdder是一种以空间换时间的解决方案，其在高并发，竞争大的情况下性能更优。
     *          但是，sum方法拿到的只是接近值，追求最终一致性。如果业务场景追求高精度，高准确性，用AtomicLong。
     * 更多详细：https://www.cnblogs.com/kuotian/p/13583138.html#:~:text=LongAdder%E6%98%AF%E4%B8%80%E7%A7%8D%E4%BB%A5%E7%A9%BA%E9%97%B4%E6%8D%A2%E6%97%B6%E9%97%B4%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88%E3%80%82%20%E5%85%B6%E5%86%85%E9%83%A8%E7%BB%B4%E6%8A%A4%E4%BA%86%E4%B8%80%E4%B8%AA%E5%80%BCbase%EF%BC%8C%E5%92%8C%E4%B8%80%E4%B8%AAcell%E6%95%B0%E7%BB%84%EF%BC%8C%E5%BD%93%E7%BA%BF%E7%A8%8B%E5%86%99base%E6%9C%89%E5%86%B2%E7%AA%81%E6%97%B6%EF%BC%8C%E5%B0%86%E5%85%B6%E5%86%99%E5%85%A5%E6%95%B0%E7%BB%84%E7%9A%84%E4%B8%80%E4%B8%AAcell%E4%B8%AD%E3%80%82%20%E5%B0%86base%E5%92%8C%E6%89%80%E6%9C%89cell%E4%B8%AD%E7%9A%84%E5%80%BC%E6%B1%82%E5%92%8C%E5%B0%B1%E5%BE%97%E5%88%B0%E6%9C%80%E7%BB%88LongAdder%E7%9A%84%E5%80%BC%E4%BA%86%E3%80%82%20Method%20sum%20%28%29%20%28or%2C%20equivalently%2C,total%20combined%20across%20the%20variables%20maintaining%20the%20sum.
     */
    static LongAdder longAdder = new LongAdder();

    // 模拟访问网站的方法
    public static void request() throws InterruptedException {
        // 模拟访问网站耗时5毫秒
//        TimeUnit.MILLISECONDS.sleep(5);
        /**
         *  Q:分析count++非原子操作
         *      count++编译后，由3步指令执行
         *      1.获取count的值，记做A：A=count
         *      2.将A的值+1，记做B：B=A+1
         *      3.将B值赋值给count coun=B
         */
        int expectCount;
        longAdder.increment();
//        count = atomicCount++;// 其不能赋值成功------注意：TODO
    }
    public static Long getCount(){return count;}

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        // 线程数量
        Integer threadSize = 100;
        // 设定初始值大小为threadSize   类似于计数器功能
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        Thread thread = null;
        List<Thread> threads = new ArrayList<>();
        // 模拟100个用户来访问网站，每个用户访问10次
        for (int i = 0; i < userNum; i++) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 模拟每个用户访问 10次
                    try {
                        for (int j = 0; j < 1000000; j++) {
                            request();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();// 每个用户访问完后递减
                    }
                }
            });
            thread.start();
//          方式一
//            threads.add(thread);
//            thread.join();// 此方法比较影响性能，原因：每次下一个线程要等待上一个线程执行完才开始执行 TODO
        }
//        thread.join(); // 当放入这里时，还有少量线程未执行完，下面的代码就已经执行完，所以count数会不对 TODO

//          方式二
//        threads.forEach(t->{t.start();
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });

//          方式三
        // 重新遍历，加入父线程，优于在上面遍历加入父线程。原因：每次下一个线程要等待上一个线程执行完才开始执行 TODO
//        threads.forEach(t-> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });

        // 怎么保证100个线程执行 结束之后,在执行后面代码 利用CountDownLatch
        countDownLatch.await();// 一直等到每个用户访问完后，即为0，才执行下面代码
        long endTime = System.currentTimeMillis();
        long doTime = endTime - startTime;
        System.out.println("耗时：" + doTime + "毫秒 ,访问总量：" + longAdder.longValue());
    }

}
