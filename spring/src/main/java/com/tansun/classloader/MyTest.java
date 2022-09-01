package com.tansun.classloader;

class Singleton {

    /* case 1 */

    private static Singleton singleton = new Singleton();

    public static int counter1;

    public static int counter2 = 0;



    /**

     * case 2

     * public static int counter1 = 0;

     * public static int counter2 = 0;

     * private static Singleton singleton = new Singleton();

     */

    public Singleton() {

        System.out.println("into private constructor method");

        counter1++;

        counter2++;

    }



    public static Singleton getInstance() {

        return singleton;

    }

}



public class MyTest {

    /**
     * 案例说明：
     *      通过类加载，初始变量（对未 初始化的变量进行初始化，对已经 初始化的变量不在初始化
     *      要初始化变量，就要调用构造方法，哪怕是private的构造方法，分配内存空间）
     *
     * @param args
     */

    public static void main(String[] args) {

        // 类的初始化--观察变量值
        initMethod();

        // 类的实例化--观察变量值
//        instanceMethod_byModifyConstuctor_isPublic();


    }

    /**
     * 展现类加载 初始化后的变量值
     */
    private static void initMethod() {
        // Singleton类实例，虽是 private 的构造方法，但在类加载时，singleton已经被初始化
        Singleton singleton = Singleton.getInstance();

        System.out.println("private constructor method do ?");

        System.out.println("counter1 = " + singleton.counter1);

        System.out.println("counter2 = " + singleton.counter2);
    }

    /**
     * 展现类的实例化，需先经过初始化
     */
    private static void instanceMethod_byModifyConstuctor_isPublic() {
        Singleton singleton1 = new Singleton();

        System.out.println("counter1 = " + singleton1.counter1);

        System.out.println("counter2 = " + singleton1.counter2);
    }

}