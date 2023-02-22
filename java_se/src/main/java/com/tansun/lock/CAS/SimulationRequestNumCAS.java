package com.tansun.lock.CAS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 模拟100个用户，每个用户访问10次，最后统计总访问量
 */
public class SimulationRequestNumCAS {

    // 模拟网站总访问量 --volatile保证变量的可见性，从主内存中重新拉取变量
    private volatile static Integer count = 0;
    // 用户数
    private static Integer userNum = 100;

    /**
     * Q:耗时太长的原因？
     *      request()加入synchronized关键字，锁住的对象是this本类
     *      保证了程序的正确执行结果，但是耗时太长
     *
     * Q:如何解决耗时太长？
     *      count++编译后，由3步指令执行
     *      1.获取count的值，记做A：A=count
     *      2.将A的值+1，记做B：B=A+1
     *      3.将B值赋值给count coun=B
     *      升级第三步的实现：
     *          1.获取锁
     *          2.获取count的最新值，记做LV
     *          3.判断LV是否与期望值A相等，如果相等，则将B赋值给A，并返回true,否则返回false
     *          4.释放锁
     *
     * @throws InterruptedException
     */
    // 模拟访问网站的方法
    public static void request() throws InterruptedException {
        // 模拟访问网站耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        /**
         *  Q:分析count++非原子操作
         *      count++编译后，由3步指令执行
         *      1.获取count的值，记做A：A=count
         *      2.将A的值+1，记做B：B=A+1
         *      3.将B值赋值给count coun=B
         */
//        count++;
        //将count++拆分 当期望值与count值相等时，将新的值赋给count,否则一直循环判断，直到成功为止
//        int expectCount = getCount();//期望值    ---注意：这样的赋值是有问题的 TODO
        int expectCount;//期望值
        // 如果修改不成功，就一直修改
        while (!compareAndSwap(expectCount = getCount(),expectCount + 1)){}
    }

    /**
     *
     * @param expectCount 期望值
     * @param newCount 要改变成的值
     * @return true表示修改成功，false表示修改失败
     */
    public synchronized static boolean compareAndSwap(int expectCount, int newCount){
        // count与expectCount期望值相等，将newCount赋给count
        if (getCount() == expectCount){
            count = newCount;
            return true;
        }
        return false;
    }

    public static int getCount(){return count;}

    public static void main(String[] args) throws InterruptedException {
        // 记录当前时间
        long startTime = System.currentTimeMillis();

        // 线程数量
        Integer threadSize = 100;
        // 设定初始值大小为threadSize
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        // 模拟100个用户来访问网站，每个用户访问10次
        for (int i = 0; i < userNum; i++) {
            new Thread(new Runnable() {
                public void run() {
                    // 模拟每个用户访问 10次
                    try {
                        for (int j = 0; j < 10; j++) {
                            request();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();// 每个用户访问完后递减
                    }
                }
            }).start();
        }
        // 怎么保证100个线程执行 结束之后,在执行后面代码 利用CountDownLatch
        countDownLatch.await();// 一直等到每个用户访问完后，即为0，才执行下面代码
        long endTime = System.currentTimeMillis();
        long doTime = endTime - startTime;
        System.out.println("耗时：" + doTime + "毫秒 ,访问总量：" + count);
    }

}
