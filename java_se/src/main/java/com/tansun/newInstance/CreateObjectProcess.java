package com.tansun.newInstance;

public class CreateObjectProcess {

    private int j = getI();// 实例变量j 在实例变量i初始化之后，未实例化之前，进行了赋值
    private int i = 3; // 在实例化 实例变量j之后，对i进行实例化



    {
        ++i;
        System.out.println("代码块i="+i);
    }

    static {
        System.out.println("static");
    }

    public CreateObjectProcess(){
        i = 5;
        System.out.println("构造函数i="+i);
    }

    private int getI() {
        return i;
    }

    public static void main(String[] args) {
        CreateObjectProcess createObjectProcess = new CreateObjectProcess();
        // 对象创建
        System.out.println("变量i现只被初始化了，还未被实例化："+createObjectProcess.j);
        System.out.println(createObjectProcess.i);
    }
}
