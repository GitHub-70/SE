package com.tansun.lock.CAS;

import java.lang.invoke.MethodType;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 模拟100个用户，每个用户访问10次，最后统计总访问量
 */
public class SimulationRequestNumberAtomicStampedReference {

    // 模拟网站总访问量
    private static Integer count = 0;
    // 用户数
    private static Integer userNum = 100;
    // 版本号
    private static Integer stamp = 1;
    // 将普通类Integer升级为原子类
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(count,stamp);

    // 模拟访问网站的方法
    public static void request(Integer selectMethodType) throws InterruptedException {
        // 模拟访问网站耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        /**
         *  Q:分析count++非原子操作
         *      count++编译后，由3步指令执行
         *      1.获取count的值，记做A：A=count
         *      2.将A的值+1，记做B：B=A+1
         *      3.将B值赋值给count coun=B
         */
        // 如果没有修改成功，就一直修改
//        count = atomicStampedReference.getReference();
//        stamp = atomicStampedReference.getStamp();
        while (!atomicStampedReference.compareAndSet(count = atomicStampedReference.getReference(),count+1, stamp = atomicStampedReference.getStamp(), stamp +1)){

        }
        System.out.println("count==="+count);
//        System.out.println("stamp==="+stamp);
//        switch (selectMethodType){
//            case 1:
//                method1();
//                break;
//            case 2:
//                method2();
//                break;
//            case 3:
//                method3();
//                break;
//            case 4:
//                method4();
//                break;
//            case 5:
//                method5();
//                break;
//        }

    }


    public static Integer getCount(){return count;}

    public static void main(String[] args) throws InterruptedException {
        // 记录当前时间
        long startTime = System.currentTimeMillis();

        // 线程数量
        Integer threadSize = 100;
        // 设定初始值大小为threadSize   类似于计数器功能
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        Thread thread = null;
        // 模拟100个用户来访问网站，每个用户访问10次
        for (int i = 0; i < userNum; i++) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 模拟每个用户访问 10次
                    try {
                        for (int j = 0; j < 10; j++) {
                            request(5);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();// 每个用户访问完后递减
                    }
                }
            });
            thread.start();
//            thread.join();// 此方法比较影响性能，原因：每次下一个线程要等待上一个线程执行完才开始执行 TODO
        }
//        thread.join(); // 当放入这里时，还有少量线程未执行完，下面的代码就已经执行完，所以count数会不对 TODO

        // 怎么保证100个线程执行 结束之后,在执行后面代码 利用CountDownLatch
        countDownLatch.await();// 一直等到每个用户访问完后，即为0，才执行下面代码
        long endTime = System.currentTimeMillis();
        long doTime = endTime - startTime;
        System.out.println("耗时：" + doTime + "毫秒 ,访问总量：" + count);
    }


    /**
     * ---------------------------------------------------分割线-----------------------------------------------
     */

    private static void method1(){
        while(true){
            count = atomicStampedReference.getReference();
            stamp = atomicStampedReference.getStamp();
            if (atomicStampedReference.compareAndSet(count,count+1, stamp,stamp +1)){
                System.out.println("count===="+count);
                break;
            }
        }
    }

    private static void method2(){
        while(true){
            if (atomicStampedReference.compareAndSet(count,count+1, stamp,stamp +1)){
                stamp = atomicStampedReference.getStamp();
                count = atomicStampedReference.getReference();
                System.out.println("count===="+count);
                break;
            }
        }
    }

    private static void method3(){
        while(true){
            count = atomicStampedReference.getReference();

            if (atomicStampedReference.compareAndSet(count,count+1, stamp,stamp +1)){

                stamp = atomicStampedReference.getStamp();
                System.out.println("count===="+count);
                break;
            }
        }
    }

    private static void method4(){
        while(true){
            stamp = atomicStampedReference.getStamp();

            if (atomicStampedReference.compareAndSet(count,count+1, stamp,stamp +1)){

                count = atomicStampedReference.getReference();
                System.out.println("count===="+count);
                break;
            }
        }
    }
    private static void method5(){
        while(true){
            if (atomicStampedReference.compareAndSet(count = atomicStampedReference.getReference(),count+1, stamp = atomicStampedReference.getStamp(),stamp +1)){
                System.out.println("count===="+count);
                break;
            }
        }
    }
}
