package com.tansun.java8characteristics.lambda;

public class LambdaTest2 {

   static int number = 1;
    public static void main(String[] args) {
//        use_LocalVariable1_Filnal();
        use_LocalVariable2_Filnal();

    }

    /**
     * lambda 表达式中，引用的局部变量，具有 隐式 的final
     *                 引用的成员变量则不具有
     */
    private static void use_LocalVariable1_Filnal() {
        int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num + number));
        s.convert(2);

        // 给变量重新赋值
//        num = 5;
        number = 7;
    }

    private static void use_LocalVariable2_Filnal() {
        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);
    }

}

 interface Converter<T1, T2> {
    void convert(int i);
}