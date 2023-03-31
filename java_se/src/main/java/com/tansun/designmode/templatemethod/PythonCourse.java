package com.tansun.designmode.templatemethod;
/**
 * 钩子方法的实现类
 */
public class PythonCourse extends AbastractCourse {

    @Override
    protected void checkHomework() {
        System.out.println("检查Python作业");
    }
}
