package com.tansun;

import java.nio.channels.SelectionKey;
import java.util.Scanner;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName A
 * @date 2020/11/18 20:25
 * @Copyright © 2020 阿里巴巴
 */
public class A {
    public static int a;
    String name;

    public static void Ac(String name){
        B.a();
        // 父类引用指向子类对象
        B b = new Bi();
        // 子类Bi的:ab方法
        b.ab();
        // 父类：B类的静态方法a被调用
        b.a();
    }

    public static void main(String[] args) {
        Ac(null);

        // 静态方法可以被 对象调用，但静态属性却不能被 对象调用
//        A aa = new A();
//        aa.Ac(null);
//        aa.a;
    }

    static class Bi extends B{
        @Override
        public void aa() {

        }
        public void ab(){
            System.out.println("子类Bi的:ab方法");
        }
    }

    interface AA{
       public static final String AB = ""; // 变量默认修饰符 public static final
       public void b();
       void bb();
       default void bbb(){};    // default 修饰必须有方法体
       public static void ac(){}; // static 修饰必须有方法体
       public abstract void ab(); // 接口中的默认修饰符 public abstract
    }

}
abstract class B{

    static int b;

     public static void a(){
         System.out.println("B类的静态方法a被调用");
     };

    /**
     * static 方法是类方法,它是不允许子类覆写（override）
     * 而abstract方法是没有实现的方法，是必须由子类来覆写的。
     * 能这么设就矛盾了
     */
    public abstract void aa(); // static与abstract不能同时修饰方法
    public void ab(){
        System.out.println("父类--B的:ab方法");
    };
}