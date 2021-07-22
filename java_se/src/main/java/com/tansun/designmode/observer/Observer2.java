package com.tansun.designmode.observer;

/**
 * 被观察者2
 */
public class Observer2 implements Observer {

    int stastus1;
    int stastus2;

    @Override
    public void update(int stastus1, int stastus2) {
        this.stastus1 = stastus1;
        this.stastus2 = stastus2;
        // Interger转换为 八进制数
        System.out.println("被观察者2状态stastus1：toOctalString=="+Integer.toOctalString(this.stastus1));
        // 返回2的最大次方 的数，且这个数小于等于 传入值
        System.out.println("被观察者2状态stastus2：highestOneBit=="+Integer.highestOneBit(this.stastus2));
        /**
         * 算法思想：
         *      1.这个数是2进制数
         *      2.最大位的2进制数
         *      即 取这个数的 最大位 二进制数，其他的bit位 都为零
         * 实现：
         *      1.fori遍历 2的i次方（1 << i） 与 传入值 进行比较
         *                               当大于 传入值 取（1 << i-1）
         *                               当等于 传入值 取（1 << i）
         *      2.利用二进制的 或（与） 运算，将其他的bit位 都为零
         *      jdk作者采用的这种算法
         */
    }

}
