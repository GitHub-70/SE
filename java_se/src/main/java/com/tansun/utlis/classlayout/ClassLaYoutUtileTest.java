package com.tansun.utlis.classlayout;

import com.tansun.pool.ThreadPool;
import org.openjdk.jol.info.ClassLayout;

/**
 * 用于分析对象在jvm大小的分布情况
 */
public class ClassLaYoutUtileTest {

    static void print(String message){
        System.out.println(message);
//        System.out.println("-----------------------------------------------------------------");
    }

    public static void main(String[] args) throws InterruptedException {

        /**
         * BiasedLocking 匿名偏向锁----刚new出来的对象，不知偏向哪个线程（不知哪个线程处理）
         * 当jvm启动4秒后,这4秒是为了判断 new的对象是否还需要偏向锁
         * 如果明确知道有多线程竞争，就不升级为偏量锁了，若为偏向锁了，还要进行锁撤销，锁升级的过程
         * -XX:BiasedLockingStartupDelay=0 此参数直接让new的对象自动升级为偏向锁 延迟时间为0秒
         *      偏向锁由来：多数情况下，synch方法只有一个线程在执行，如：StringBuffe中synch方法
         *                  没有明显的资源竞争，直接升级为偏向锁
         */

//        Thread.sleep(4000);
//        Thread.sleep(4200);

        Object o = new Object();// 无所状态--第一个字节码为 00000001
        // 利用ClassLayout工具类，解析实例,并转成可打印的内容输出
        print(ClassLayout.parseInstance(o).toPrintable());
        // 查看对象外部信息
//        print(GraphLayout.parseInstance(o).toPrintable());


        /**
         * synchronized是可重入的，独占锁、非公平锁
         * 锁定某个对象，修改的是markword-（8字节）的内容
         * markword的内容：锁状态信息、hashcode、GC信息（分代年龄）
         * 锁升级过程：  new对象：无锁状态 001
         *                              偏向锁   101       偏向线程ID（）
         *                涉及到多线程：轻量锁（自旋锁）00
         *                资源竞争严重(自旋10次)：重量级锁 10    进入等待队列
         * synchronized 关键字会依赖 对象内部的一个监视器锁(监视器对象锁-Monitor)
         * 所以锁住的是对象,最终会编译成 一个monitorenter 两个monitorexit字节码指令
         * （一个正常退出，一个异常退出）
         * 系统底层指令：lock cmpxchg
         */
        synchronized (o){
            // 轻量级锁（自旋锁）状态 第一个字节码最后两位 00
            print(ClassLayout.parseInstance(o).toPrintable());
        }

        ThreadPool threadPool = new ThreadPool(3, 3, 4, null, null);
        System.out.println(ClassLayout.parseInstance(threadPool).toPrintable());

    }
}
