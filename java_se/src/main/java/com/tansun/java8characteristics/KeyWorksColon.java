package com.tansun.java8characteristics;

import java.util.function.Function;

/**
 *  :: 关键字
 * 总结：我们可以把类Something中的方法static String startsWith(String s){...}、
 *      String endWith(String s){...}、Something(String something){...}
 *      看作是接口IConvert的实现，因为它们都符合接口定义的那个 “模版”，
 *      有传参类型F以及返回值类型T。比如构造方法Something(String something)，
 *      它传参为String类型，返回值类型为Something。注解@FunctionalInterface
 *      保证了接口有且仅有一个抽象方法，所以JDK能准确地匹配到相应方法。
 */
public class KeyWorksColon {

    public static void main(String[] args) {
        getStaticMethod();
        new KeyWorksColon().getObjectMethod();
        getConstructorMethod();


    }

    /**
     * 访问静态方法
     */
    private static void getStaticMethod() {
        // static methods
        IConvert<String, String> convert = Something :: startsWith;
        String converted = convert.convert("123");
        System.out.println(converted);
    }

    /**
     * 访问对象方法
     */
    private void getObjectMethod() {
        // Object methods
        Something something = new Something();
        IConvert<String, String> convert = something :: endWith;
        String converted = convert.convert("java");
        System.out.println(converted);
        System.out.println("---------------------------------------");
        // 等号右边的类型  应 与左边接口中方法的返回值类型匹配
        IConvert<String, String> convert1 = para -> System.getProperty("user.dir");
        String converted1 =convert1.convert("");
        System.out.println(converted1);
    }

    /**
     * 访问构造方法
     */
    private static void getConstructorMethod() {

        // constructor methods
        IConvert<String, Something> convert = Something::new;
        Something something = convert.convert("constructors");
    }
}

class Something {

    // constructor methods
    Something() {}

    Something(String something) {
        System.out.println(something);
    }

    // static methods
    static String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }

    // object methods
    String endWith(String s) {
        return String.valueOf(s.charAt(s.length()-1));
    }

    void endWith() {}
}


/**
 * @FunctionalInterface 注解要求接口有且只有一个抽象方法，
 * JDK中有许多类用到该注解，比如 Runnable，它只有一个 Run 方法。
 *                            Function, 一元函数，有返回值
 *                            Consumer, 一元函数，无返回值
 *                            Predicate, 一元函数，布尔值
 *                            Supplier,
 *                            BiFunction, 二元函数
 * @param <F>
 * @param <T>
 *     观察接口 IConvert，传参为类型 F，返回类型 T。
 *     所以，我们可以这样访问类Something的方法：
 */
@FunctionalInterface
interface IConvert<F, T> {
    T convert(F form);
}