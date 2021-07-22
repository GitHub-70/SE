package com.tansun.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * 捕获父类异常信息，可抛出子类异常信息
 */
public class ExceptionTest {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

    public static void main(String[] args) {
       try {
            // 日志输出的正确方式
//           String s = nullPointerExceptionBack();
//            logger.info(s);
            // 日志输出的正确方式
//           nullPointerException();


        ExceptionTest exceptionTest = new ExceptionTest();
        System.out.print("请输入您的年龄：");
        System.out.println("您的年龄：" + exceptionTest.inputAge(false));

           doException();
       } catch (RuntimeException e){
           logger.error("main方法:{}",e.getMessage());
       }
    }

    public int inputAge(boolean isThrow) {
        int result = -1;
        if (isThrow) {
            Scanner scan = new Scanner(System.in);
            while (true) {
                try {
                    result = scan.nextInt();
                    if (result < 0 || result > 130) {
//                        new Exception("年龄超出合理范围！");
                        throw new Exception("年龄超出合理范围！！！");
                    }
                    break;
                } catch (Exception e1) {
                    System.out.print(e1.getMessage() + "请重新输入：");
                }
            }
        } else {
            Scanner scan = new Scanner(System.in);
            while (true) {
                result = scan.nextInt();
                if (result < 0 || result > 130) {
                    // 无任何效果
                    new Exception("年龄超出合理范围！");
                    System.out.print("请重新输入您的年龄：");
                } else {
                    break;
                }
            }
        }
        return result;
    }

    public static void doException(){
        try {
            int num = 5;
            System.out.println("请第一次输入被除数：");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            int result = num / number;
            // 请第一次输入被除数异常，未能被执行的代码
            logger.info("result={}", result);

        } catch (RuntimeException e){
            // 可以什么也不做
            e.printStackTrace();// 程序员看的日志
            logger.error(e.getMessage());
            // todo 如果不在 catch 中抛出异常，还会继续执行下面代码
//            throw new RuntimeException(e.getMessage());
        }

        try {
            // 请第一次输入被除数异常，能被执行的代码
            if (true){
                int num = 4;
                System.out.println("请第二次输入被除数：");
                Scanner scanner = new Scanner(System.in);
                int number = scanner.nextInt();
                int result = num / number;
                logger.info("result={}", result);
            }

            // 请第二次输入被除数异常，未能被执行的代码
            int num3 = 3;
            System.out.println("请第三次输入被除数：");
            Scanner scanner3 = new Scanner(System.in);
            int number3 = scanner3.nextInt();
            int result3 = num3 / number3;
            logger.info("result3={}", result3);
        } catch (RuntimeException e){
            System.out.println("捕获了异常信息，未抛出");
        }
    }

    /**
     * 该方法出现异常
     * 调用该方法的上层代码 不会往下继续执行
     * @return
     * @throws RuntimeException
     */
    public static void nullPointerException(){
        try {
            ExceptionTest exceptionTest1 = null;
            if (null == exceptionTest1){
                logger.error("exceptionTest1对象为null");
                throw new NullPointerException("exceptionTest1对象为null");
            }
            exceptionTest1.inputAge(true);
        } catch (RuntimeException e){
            // 让程序员看的日志，可什么都不做
            logger.error("正确的抛出错误日志，捕获日志：" + e.getMessage());
            throw  new RuntimeException(e.getMessage());
        }
    }

    /**
     * 该方法出现异常
     * 调用该方法的上层代码 还会继续执行下面代码
     * @return
     * @throws RuntimeException
     */
    public static String nullPointerExceptionBack() throws RuntimeException{
        try {
            ExceptionTest exceptionTest1 = null;
            if (null == exceptionTest1){
                logger.error("exceptionTest1对象为null");
                throw new NullPointerException("exceptionTest1对象为null");
            }
            exceptionTest1.inputAge(true);
        } catch (RuntimeException e){
            // 让程序员看的日志，可什么都不做
            logger.error("正确的抛出错误日志，捕获日志：" );
            // todo 如果不在 catch 中抛出异常，调用该方法的上层代码 还会继续执行下面代码
//            throw  new RuntimeException(e.getMessage());
        }
        return "23";
    }


}
