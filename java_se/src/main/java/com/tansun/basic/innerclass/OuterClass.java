package com.tansun.basic.innerclass;

/**
 * 内部类无条件访问外部类元素
 * 实现信息隐藏
 * 通过匿名内部类来优化简单的接口实现
 */
public class OuterClass {

    private String data = "外部类数据";

    /**
     * private修饰内部类，实现信息隐藏
     */
    private class InnerClass implements InnerInterface {

        @Override
        public void innerMethod() {
            System.out.println("实现内部类隐藏");
        }

        public InnerClass(){
            // 内部类无条件访问外部类元素
            System.out.println(data);
        }

        public void innerGeneralMethod(){
            System.out.println("内部类的普通方法！");
        }
    }


    public InnerInterface getInner() {

        return new InnerClass();

    }



    public static void main(String[] args) {

        OuterClass outerClass = new OuterClass();

        // OuterClass的getInner()方法能返回一个InnerInterface接口实例，但我并不知道这个实例是这么实现的。
        InnerInterface inner = outerClass.getInner();
        inner.innerMethod();



        // 通过匿名内部类来优化简单的接口实现

    }

}