package com.tansun.designmode.templatemethod;

public class TemplateMethodTest {
    public static void main(String[] args) {
        System.out.println("=========架构师课程=========");
        JavaCourse java = new JavaCourse();
        // 控制钩子方法的执行
        java.setNeedCheckHomework(false);
        java.createCourse();


        System.out.println("=========Python课程=========");
        PythonCourse python = new PythonCourse();
        python.createCourse();
    }

}
