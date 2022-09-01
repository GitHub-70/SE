package com.tansun.java8characteristics;

import com.tansun.utlis.RetryUtils;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class FunctionTest {


    public static void main(String[] args) throws Exception {

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

//        test(fnc);
    }

    public static  void test(Function<Integer,Integer> test){
        test= i-> i + 1;
        System.out.println(test.apply(5));
    }


}

class Something2<T,R> {

    public String convert2(String form) {

        try {
            for (int i = 0; i < 2; i++) {
                if (i == 2) {
                    return form;
                }
                throw new RuntimeException("convert2方法执行异常");
            }
        } catch (RuntimeException e){
            throw new RuntimeException("convert2方法执行异常");
        }
        return form;
    }
}
