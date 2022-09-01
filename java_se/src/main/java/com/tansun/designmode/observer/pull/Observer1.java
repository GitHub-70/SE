package com.tansun.designmode.observer.pull;

import java.util.Scanner;

/**
 * 观察者1(监听器-相当于zookeeper注册中心)
 *      提供观察者的状态，及更新方法
 */
public class Observer1 implements Observer {

    int stastus1;
    int stastus2;

    @Override
    public void update(int stastus1, int stastus2, String message, Subject observable) {
        this.stastus1 = stastus1;
        this.stastus2 = stastus2;
        //将 Interger转换为 二进制数
        System.out.println("观察者1状态stastus1：toBinaryString=="+Integer.toBinaryString(this.stastus1));
        //将 Interger转换为 十六进制数
        System.out.println("观察者1状态stastus2：toHexString=="+Integer.toHexString(this.stastus2));

        // 基于事件（即update）的 回调函数，回调 被观察者的回调函数
        System.out.println("是否进行客户端升级？ 输入 1 则进行升级，否则不升级");
        Scanner input = new Scanner(System.in);
        if (input.next().equals("1")){
            observable.clientUpgrade_pull();
        }
    }

}
