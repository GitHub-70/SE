package com.tansun.thread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalTest {


    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    // 10个线程
    static ExecutorService executorServiece = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        // 线程池外，先set一个变量
        String a = "abc";
        threadLocal.set(a);
        System.out.println(threadLocal.get());

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Future futures = executorServiece.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    String name = Thread.currentThread().getName();
                    if (threadLocal.get()== null){
                        threadLocal.set(String.valueOf(finalI+"ABCABC"));
                    }

                    // 线程1 休眠5秒，其他线程获取不到 线程本地变量
                    if ("pool-1-thread-1".equals(name)){
                        Thread.sleep(5000);
                    }

                    // 抛出异常，释放线程本地变量
                    if (finalI==3){
                        try {
                            throw new RuntimeException("当前线程："+name);
                        }catch (Exception e){
                            System.out.println("抛出的异常信息："+ e.getMessage());
                        } finally {
                            System.out.println("finally最终执行！");
                            threadLocal.remove();
                        }
                    }

                    System.out.println("当前线程：" + name+"---i值："+ finalI+"----threadloal:"+ threadLocal.get());
                    return null;
                }
            });
        }

        executorServiece.shutdown();
    }
}
