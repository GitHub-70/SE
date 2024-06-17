package com.tansun.thread.pool;

import java.util.concurrent.*;

public class ThreadPool extends ThreadPoolExecutor {

    /**
     * 自定义线程池 自定义拒绝策略
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue new ArrayBlockingQueue<>(5); // 有界队列； new LinkedBlockingDeque<>() 无界队列
     * @param handler
     * AbortPolicy（默认策略）：默认的拒绝策略，会直接抛出RejectedExecutionException异常，
     *                         阻止新任务的提交。这是线程池默认的拒绝策略。
     * CallerRunsPolicy：当任务被拒绝时，由提交任务的线程来执行该任务。这样可以降低新任务的提交速度，
     *                  但不会丢失任务。
     * DiscardPolicy：直接丢弃被拒绝的任务，不做任何处理。这种策略可能会导致任务丢失。
     * DiscardOldestPolicy：丢弃队列中等待时间最长的任务，然后将被拒绝的任务添加到队列中。
     * Custom策略：自定义拒绝策略，根据业务需求实现RejectedExecutionHandler接口，
     *             自定义处理被拒绝的任务。可以根据实际情况选择丢弃最旧的任务、丢弃最新的任务、阻塞等待等策略。
     *
     */
    public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        // 无界队列，CallerRunsPolicy：当任务被拒绝时，由提交任务的线程来执行该任务。这样可以降低新任务的提交速度，但不会丢失任务。
        super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, handler);
    }

    /**
     * 采用默认策略
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     */
    public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        // 无界队列，CallerRunsPolicy：当任务被拒绝时，由提交任务的线程来执行该任务。这样可以降低新任务的提交速度，但不会丢失任务。
        super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
    }

    /**
     * 自定义线程工厂，及线程拒绝策略
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param threadFactory
     * @param handler
     */
    public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        // 无界队列，CallerRunsPolicy：当任务被拒绝时，由提交任务的线程来执行该任务。这样可以降低新任务的提交速度，但不会丢失任务。
        super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, threadFactory, handler);
    }
}
