package com.tansun.thread.pool.status;

/**
 * 线程状态
 *
 * @ClassName ThreadStatus
 * <p>
 * Java中的线程在其生命周期中可以处于以下几种状态：
 * 1.NEW： 当线程对象创建但尚未启动时，线程处于NEW状态。一旦Thread类的start()方法被调用，
 * 线程将从NEW状态转换为RUNNABLE状态。
 * 2.RUNNABLE： 在RUNNABLE状态下，线程可能正在运行，也可能正在等待CPU分配时间片。当线程处于这个状态时，
 * 它可能因为各种原因而暂停，如I/O操作或等待锁，但这些暂停不会改变线程的状态。
 * 3.BLOCKED：  这个状态通常指的是线程正在等待锁以进入同步块或方法。当线程尝试进入一个由synchronized关键字
 * 保护的代码段，但锁已被其他线程占用时，线程将处于BLOCKED状态。
 * 4.WAITING： 线程进入WAITING状态是因为它在等待另一个线程执行特定的动作，如调用Object.wait()方法。
 * 这种等待没有时间限制，直到其他线程调用Object.notify()或Object.notifyAll()来唤醒线程。
 * 5.TIMED_WAITING： 类似于WAITING状态，但是线程将在一定的时间之后自动恢复到RUNNABLE状态，
 * 例如调用Thread.sleep(long millis)或Object.wait(long timeout)。
 * 6.TERMINATED： 当线程完成其任务或由于某种异常而停止时，线程将进入TERMINATED状态。
 *
 * 值得注意的是，线程在执行synchronized代码块或调用ReentrantLock.lock()时是不能被中断的，
 * 因为中断状态是在JVM级别处理的，而锁的持有状态不会受到中断的影响。
 * 但是，线程在等待锁释放或调用ReentrantLock.tryLock(long time, TimeUnit unit)时可以被中断。
 *
 */
public class ThreadStatus {


    public static void main(String[] args) throws InterruptedException {
//        interrupt();

//        isInterrupted();

//        interrupted();

        interruptPostSleep();
    }

    /**
     * 线程中断后，如果线程处于sleep状态，则会抛出InterruptedException异常，
     * 并且会清除中断状态。
     */
    private static void interruptPostSleep() {
        Thread.currentThread().interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){

            System.out.println(Thread.currentThread().isInterrupted()); // false
            e.printStackTrace();
        }
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());// true
        // interrupted() 方法用于判断线程是否被中断，被中断返回true，并清除中断状态
        System.out.println(Thread.interrupted());// true
        System.out.println(Thread.interrupted());// false
        System.out.println(Thread.interrupted());// false
    }

    /**
     * interrupted() 方法用于判断线程是否被中断，被中断返回true，并清除中断状态(即设置为false)。
     * @throws InterruptedException
     */
    private static void interrupted() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted.");
                    return;
                }
                System.out.println("Thread is running...");
            }
        });

        t.start();
        Thread.sleep(3000); // 等待3秒
        t.interrupt(); // 发送中断请求
    }

    /**
     * isInterrupted() 方法用于检查线程是否被中断，但不会清除中断状态。
     * @throws InterruptedException
     */
    private static void isInterrupted() throws InterruptedException {
        Thread t = new Thread(() -> {
            boolean interruptedStatus = false;
            while (!interruptedStatus) {
                // 如果当前线程未被中断，则循环执行任务
                if (Thread.currentThread().isInterrupted()) {
                    interruptedStatus = true;
                    System.out.println("Thread was interrupted.");
                }
                try {
                    // 当前线程被设置中断后，当前线程再去睡眠时，会抛出InterruptedException异常，并且会清除中断状态
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread is or interrupt ?"+Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was InterruptedException.");
                    continue;
                }
                System.out.println("Thread is running...");
            }
        });

        t.start();
        Thread.sleep(3000); // 等待3秒
        t.interrupt(); // 发送中断请求
    }

    /**
     * 当一个线程调用另一个线程的interrupt()方法时，被调用线程的中断状态会被标记为true。
     * 如果被中断的线程正在等待、睡眠或阻塞，它将抛出一个InterruptedException。
     * @throws InterruptedException
     */
    private static void interrupt() throws InterruptedException {
        Thread t = new Thread(() -> {
            // 如果当前线程未被中断，则循环执行任务
            while (!Thread.currentThread().isInterrupted()) {
                // 模拟长时间运行的任务
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 说明当前线程处于wait状态时，被中断时，会抛出InterruptedException异常，即中断失败
                    Thread.currentThread().interrupt();// 让当前线程自己主动中断，跳出循环
                    System.out.println("Thread was interrupted.");
                    continue;
                }
                System.out.println("Thread is running...");
            }
        });

        t.start();
        Thread.sleep(3000); // 等待3秒
        t.interrupt(); // 主线程向 t 发送中断请求
        System.out.println(t.isInterrupted());
    }
}





