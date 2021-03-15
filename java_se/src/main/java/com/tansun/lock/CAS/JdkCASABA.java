package com.tansun.lock.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟100个用户，每个用户访问10次，最后统计总访问量
 *  sum.misc.unsafe类中
 *     public final native boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5);
 *     public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
 *     public final native boolean compareAndSwapLong(Object var1, long var2, long var4, long var6);
 *     参数说明：var1--表示要操作的对象
 *               var2--表示要操作对象中 属性地址值的偏移量
 *               var4--表示需要修改数据之前 的期望值
 *               var5--表示需要修改数据成所成为的新值
 *
 *
 *  CAS实现原理是什么？
 *      CAS通过JNI的代码实现，JNI:java Native Interface,允许java调用其他语言，
 *      而compareAndSwapXXX系列的方法，就是借助“C语言”来调用CPU底层指令来实现的
 *      以常用的处理器Intl X86来说，最终映射到的CPU指令为“cmpxchg”,这是一个原子指令
 *      cpu执行此命令时，实现比较并替换的操作
 *
 *  “cmpxchg”指令如何保证多核心线程下的安全？
 *      系统底层进行CAS操作时，会去判断当前系统是否为多核心线程，如果是，则就给
 *      “总线”加锁，只会有一个线程对总线加锁成功，加锁成功后，之后进行CAS操作
 *
 * CAS会有哪些问题？
 *      1.性能问题--CAS操作不成功，会一直循环，占用CPU,消耗系统资源
 *      2.ABA问题--
 *
 * 解决CAS中的ABA问题--加一个版本号
 *      其中jdk java.util.concurrent.atomic包中提供了 AtomicStampedReference
 *      AtomicStampedReference其原子类中有个 Pair（一对）内部类，其Pair定义了
 *      引用的对象类型reference 以及 版本号stamp
 *      用其compareAndSwapSet方法，返回值为比较 引用对象、版本号、Pair对象是否都一致
 *      一致则返回true,否则返回false
 */

public class JdkCASABA {

    // 原子变量初始值为1    其变量的值已经用volatile修饰
    static  AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 模拟ABA问题
     * 主线程修改数据，遇到另一线程修改数据，检测主线程有没有察觉
     * @param args
     */
    public static void main(String[] args) {
        //主线程修改数据
        Thread slaveMain = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("操作线程"+Thread.currentThread().getName()+"， 初始值："+atomicInteger.get());
                try {
                    // 设定期望值
                    int expectNum = atomicInteger.get();
                    // 新值
                    int newNum = expectNum + 1;
                    // 让此线程休眠一秒钟，让出cpu
                    Thread.sleep(1000);
                    // 主线程调用compareAndSwapSet方法修改值
                    boolean isCASSccusse = atomicInteger.compareAndSet(expectNum, newNum);
                    System.out.println("操作线程"+Thread.currentThread().getName()+"，CAS操作是否成功："+isCASSccusse);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"主线程");

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);// 确保上面的主线程 slaveMain优先执行
                    // 修改AtomicInteger值
                    atomicInteger.incrementAndGet();// 让其加1
                    System.out.println("操作线程"+Thread.currentThread().getName()+"，[increment]值："+atomicInteger.get());
                    atomicInteger.decrementAndGet();// 让其减1
                    System.out.println("操作线程"+Thread.currentThread().getName()+"，[decrement]值："+atomicInteger.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"干扰线程");
        slaveMain.start();
        other.start();
    }


}
