package com.tansun.designmode.templatemethod;

/**
 * 钩子方法的实现类
 */
public class JavaCourse extends AbastractCourse {
    private boolean needCheckHomework = false;

    public void setNeedCheckHomework(boolean needCheckHomework) {
        this.needCheckHomework = needCheckHomework;
    }

    /**
     * 子类通过钩子方法 实现扩展
     * @return
     */
    @Override
    protected boolean needCheckHomework() {
        return this.needCheckHomework;
    }

    protected void checkHomework() {
        System.out.println("检查Java作业");
    }
}
