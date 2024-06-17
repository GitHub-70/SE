package com.tansun.java8characteristics.lambda;

/**
 * java8 新特性
 * https://www.cnblogs.com/liuxiaozhi23/p/10880147.html
 * https://www.javabrahman.com/java-8/java-8-java-util-function-function-tutorial-with-examples/
 *
 * 接口 default方法由来：
 *      当需要修改接口时候，需要修改全部实现该接口的类，目前的java 8之前的集合框架没有foreach方法，
 *      通常能想到的解决办法是在JDK里给相关的接口添加新的方法及实现。然而，对于已经发布的版本，
 *      是没法在给接口添加新方法的同时不影响已有的实现。所以引进的默认方法。
 *      他们的目的是为了解决接口的修改与现有的实现不兼容的问题。
 *  一个类实现了多个接口，且这些接口有相同的默认方法：
 *      想要掉具体接口的默认方法，可以使用  具体接口名.super.默认方法 来调用指定接口的默认方法
 *
 *  Supplier<T> 无参数，返回一个泛型结果。
 */
public class LambdaTest1 {
    public static void main(String args[]) {

        LambdaTest1 tester = new LambdaTest1();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);
        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);
        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }
    interface MathOperation {
        int operation(int a, int b);
    }
    interface GreetingService {
        void sayMessage(String message);
    }
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
