package com.tansun.java8characteristics;

import com.tansun.utlis.RetryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

public class MyFunctionTest {


    public static void main(String[] args) throws Exception {

//        extracted();

        extracted2();

//        extracted3(fnc);
    }

    private static void extracted2() throws Exception {
        Something2 something2 = new Something2();
        Function<String,String> fnc = something2 :: convert2;

        List<FutureTask> futureTasks = new ArrayList<>();
        // 另起一个线程执行此任务
        for (int i = 0; i < 10; i++) {
            // 开启十个线程
            FutureTask futureTask = new FutureTask<>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String res = RetryUtils.execute("7", fnc, 2000, 3);;
                    System.out.println("其他线程输出：" + res);
                    return res;
                }
            });
            futureTasks.add(futureTask);
            new Thread(futureTask).start();
        }

        System.out.println("主线程获取子线程任务...");
        for (FutureTask futureTask : futureTasks) {
            // 阻塞主线程，获取子线程结果
            Object o = futureTask.get().toString();
            System.out.println("任务结果：" + o);
        }
        System.out.println("主线程执行结束");
    }


    private static void extracted() throws Exception {
        Something2 something2 = new Something2();
        Function<String,String> fnc = something2 :: convert2;
//        System.out.println(fnc.apply("2"));

        // 另起一个线程执行此任务
        Thread otherThread = new Thread(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String res = RetryUtils.execute("3", fnc, 2000, 3);;
                System.out.println("其他线程输出：" + res);
                return res;
            }
        }.call());
        otherThread.start();
        // 让主线程 睡一秒钟
        Thread.sleep(1000);
        System.out.println("主线程输出：10");
    }

    public static  void extracted3(Function<Integer,Integer> test){
        test= i-> i + 1;
        System.out.println(test.apply(5));
    }


}

/**
 * 定义一个消费者函数 入参为 T 返回值为 R
 * @param <T>
 * @param <R>
 */
class Something2<T,R> {

    public String convert2(String form) {

        try {
            for (int i = 0; i < 2; i++) {
                if (i == 2) {
                    return form;
                }
                // 当前线程 为 "Thread-0" 时，抛异常
                if ("Thread-0".equals(Thread.currentThread().getName()))
                throw new RuntimeException("convert2方法执行异常");
            }
        } catch (RuntimeException e){
            throw new RuntimeException("convert2方法执行异常");
        }
        return form;
    }
}
