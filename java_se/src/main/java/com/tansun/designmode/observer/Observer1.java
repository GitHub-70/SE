package com.tansun.designmode.observer;

/**
 * 被观察者1
 */
public class Observer1 implements Observer {

    int stastus1;
    int stastus2;

    @Override
    public void update(int stastus1, int stastus2) {
        this.stastus1 = stastus1;
        this.stastus2 = stastus2;
        System.out.println("被观察者1状态stastus1：toBinaryString=="+Integer.toBinaryString(this.stastus1));
        System.out.println("被观察者1状态stastus2：toHexString=="+Integer.toHexString(this.stastus2));
    }

}
