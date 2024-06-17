package com.tansun.numerical.law;

import com.google.common.util.concurrent.AtomicDouble;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class TestMain {

    static ExecutorService executorService = Executors.newFixedThreadPool(16);

    static AtomicLong k = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {

//        extracted3();

//        extracted();

//        extracted1();

//        extracted2();

//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String line = scanner.nextLine();
//            if ("exit".equals(line)) {
//                break;
//            } else {
//                System.out.println("输出：" + line);
//            }
//        }

        int cros = Runtime.getRuntime().availableProcessors();
        System.out.println(cros);

    }

    private static void extracted3() throws InterruptedException {
        int n = 100000000;
        int procesNum = Runtime.getRuntime().availableProcessors();
        CountDownLatch countDownLatch = new CountDownLatch(procesNum);
        for (int i = 0; i < procesNum; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        calculatePai(k, n);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(k.get());
        Double pai = Double.valueOf(k.get() * 4) / Double.valueOf(procesNum * n);
        System.out.println(String.format("圆周率π：%s",pai));
    }

    private static void isAdd(ArrayList<Object> objects) {
        objects.add(1234);
    }


    /**
     * 对 对象的 hashcode 值取模
     * 根据用户id的hash值对数据库的数量进行取模找到对应的数据库->
     * 根据用户id的hash值除以对表的数量，然后在对表的数量进行取模即可找到对应的表、
     * 疑问：取 hashcode 之后，对 n 取余，到底要不要+1 ？ 表号是从 1开始 n 结束
     */
    private static void extracted2() {
        String client = "123456789123456789123";
        int clientHashCode = client.hashCode(); // 145174162
        int dbNum = 3;
        int userTableNum = 4;
        System.out.println("clientHashCode=" + clientHashCode);
        System.out.println(clientHashCode % dbNum + 1); // 2
        // 这里会当字符串 拼接到后面
        System.out.println("分库号：" + clientHashCode % dbNum + 1); // 1
        System.out.println("分表号：" + clientHashCode / dbNum % userTableNum); // 3
    }

    /**
     * 二的幂 取模求余
     * 二进制数 取余
     */
    private static void extracted1() {
        int total = 6;
        System.out.println(2 & (total - 1));
    }

    private static void extracted() {
        boolean result = true;

        List<Boolean> booleans = new ArrayList<>();
        booleans.add(isResult(result));
        // result传入到方法isResult中，并且返回，在上层方法是不可见了，所以还是true
        System.out.println("result:" + result);
        for (Boolean aBoolean : booleans) {
            System.out.println("aBoolean:" + aBoolean);
            if (!aBoolean) {
                result = aBoolean;
            }
        }
    }

    private static boolean isResult(boolean result) {
        result = false;
        return result;
    }

    /**
     * 计算圆周率
     *
     * @param k 落在1/4正方形内切圆中的总次数
     * @param n 落在正方形中的总次数
     * @return
     */
    private static Long calculatePai(AtomicLong k, int n) {
        Random random = new Random();// 生成随机数
//        int n = 1000000000;// 执行次数
//        int k = 0;// 落在范围内的个数。
        for (int i = 0; i < n; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (x * x + y * y <= 1)
                k.incrementAndGet();
        }

//        return 4.0 * k.intValue() / n;// x，y都是正的，第一象限，所以要乘以4
//        System.out.println(String.format("当前线程[%s],获取值：[%s]", Thread.currentThread().getName(), k.get()));
        return k.get();
    }

}
