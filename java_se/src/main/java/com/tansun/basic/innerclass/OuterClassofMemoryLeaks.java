package com.tansun.basic.innerclass;

/**
 * 内部类可能引起的内存泄露
 * 那么内部类在什么情况下会造成内存泄漏的可能呢？
 *
 *  java 的内存回收机制通过「可达性分析」判断
 * 如果一个匿名内部类没有被任何引用持有，那么匿名内部类对象用完就有机会被回收。
 *
 * 如果内部类仅仅只是在外部类中被引用，当外部类的不再被引用时，外部类和内部类就可以都被GC回收。
 *
 * 如果当内部类的引用被外部类以外的其他类引用时，就会造成内部类和外部类无法被GC回收的情况，
 * 即使外部类没有被引用，因为内部类持有指向外部类的引用）。
 */
public class OuterClassofMemoryLeaks {

    /**
     * 运行程序发现 执行内存回收并没回收 object 对象，
     * 这是因为即使外部类没有被任何变量引用，只要其内部类被外部类以外的变量持有，外部类就不会被GC回收。
     *
     * 我们要尤其注意内部类被外面其他类引用的情况，这点导致外部类无法被释放，极容易导致内存泄漏
     */
    Object object = new Object() {
        public void finalize() {
            System.out.println("inner Free the occupied memory...");
        }
    };

    public void finalize() {
        System.out.println("Outer Free the occupied memory...");
    }
}

class TestInnerClass {

    public static void main(String[] args) {
        try {
            Test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void Test() throws InterruptedException {
        System.out.println("Start of program.");

        OuterClassofMemoryLeaks outer = new OuterClassofMemoryLeaks();
        Object object = outer.object;
        outer = null;

        System.out.println("Execute GC");
        System.gc();

        Thread.sleep(3000);
        System.out.println("End of program.");
    }
}
