package com.tansun.thread.pool;



import org.springframework.stereotype.Component;

import java.util.concurrent.*;
@Component
public class ThreadPoolExecutors {

    /**
     * 创建固定大小的线程池，线程池的大小一旦达到最大值就会保持不变，
     * 如有线程因异常而结束，那么线程池会补充一个新线程
     */
    private Executor fixedExecutor;
    /**
     * 创建一个可缓存的线程池，如果线程池的大小超过了 处理任务所需要的线程，
     * 那么就会回收空闲（默认是60秒不执行任务）的线程，当任务数增加时，此线程
     * 池又可以添加新的线程来处理任务。
     */
    private Executor cacheExecutor;
    /**
     * 该线程池只有一个线程，也就是单线程 串行执行所有任务，当该线程因异常结束时，
     * 那么会有一个新的线程来代替它；即该线程池保证了 所有任务的执行顺序，按照
     * 任务的提交顺序执行。
     */
    private Executor singleExecutor;
    /**
     * 此线程池支持定时、周期地执行任务
     */
    private Executor scheduledExecutor;
    /**
     * 此线程池支持定时、周期地执行任务
     */
    private Executor singleScheduledExecutor;
    /**
     * ExecutorService继承了Executor
     *      --新增了submit、shutdown、invoke..等方法
     */
    private ExecutorService executorServiece;



    public Executor getThreadExecutorType(String executorType){
        switch (executorType){
            case "fixedExecutor":
                if (null != fixedExecutor){
                    fixedExecutor = Executors.newFixedThreadPool(3);
                }
                return fixedExecutor;
            case "cacheExecutor":
                if (null == cacheExecutor){
                    cacheExecutor = Executors.newCachedThreadPool();
                }
                return cacheExecutor;
            case "singleExecutor":
                if(null == singleExecutor){
                singleExecutor = Executors.newSingleThreadExecutor();
                }
                return singleExecutor;
            case "schelduledExecutor":
                if (null == scheduledExecutor){
                scheduledExecutor = Executors.newScheduledThreadPool(5);
                }
                return scheduledExecutor;
            case "singleScheduledExecutor":
                   if (null == singleScheduledExecutor){
                       singleScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                   }
                   return singleScheduledExecutor;
            default:
                if (null != executorServiece){
                    executorServiece = Executors.newFixedThreadPool(2);
                }
//                return fixedExecutorService;
        }
        return null;
    }

    public void doRunOrCall(){

        fixedExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类");
            }
        });
        Future futures = executorServiece.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("call()方法返回值");
                return null;
            }
        });

        Future future = executorServiece.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("submit()方法返回的是Future对象");
            }
        });

    }



}
