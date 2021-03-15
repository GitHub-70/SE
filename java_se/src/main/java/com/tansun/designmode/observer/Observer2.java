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
        System.out.println("被观察者2状态stastus1：toOctalString=="+Integer.toOctalString(this.stastus1));
        System.out.println("被观察者2状态stastus2：toOctalString=="+Integer.toOctalString(this.stastus2));
    }

}
