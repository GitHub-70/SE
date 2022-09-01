package com.tansun.basic.innerclass;

/**
 * 内部类与外部类的关系:
 * 对于非静态内部类，内部类的创建依赖外部类的实例对象，在没有外部类实例之前是无法创建内部类的
 *
 * 内部类是一个相对独立的实体，与外部类不是is-a关系
 *
 * 创建内部类的时刻并不依赖于外部类的创建
 *
 * 内部类可以分为：静态内部类（嵌套类）和非静态内部类。非静态内部类又可以分为：成员内部类、方法(局部)内部类、匿名内部类。
 * 更多详情：https://www.cnblogs.com/JonaLin/p/13599412.html
 */
public class OuterInnerClass {

    public void fun(){
        System.out.println("外部类方法");
    }

    public class InnerClass{

        public void InnerClassMethod(){
            System.out.println("内部类的方法被调用了！！！");
        }

    }


    // 对于普通内部类创建方法有两种
    public static void main(String[] args) {
        //创建方式1
        OuterInnerClass.InnerClass innerClass = new OuterInnerClass().new InnerClass();
        //创建方式2
        OuterInnerClass outer = new OuterInnerClass();
        OuterInnerClass.InnerClass inner = outer.new InnerClass();

    }
}



