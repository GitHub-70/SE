package com.tansun.designmode.observer.pull;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者（相当于服务器）
 *      提供 观察者的集合
 *      提供 被观察者的状态（当被观察者状态发生改变，观察者做出相应调整）
 *      提供 观察者的注册方法
 *      提供 观察者的移除方法
 *      提供 观察者的唤醒方法
 */
public class ConcreteSubject implements Subject {

    // 被监听的状态
    private int status1;
    // 被监听的状态
    private int status2;

    // 观察者（监听器）集合
    private List<Observer> observers = new ArrayList<Observer>();

    // 当某一个被观察者状态放生改变时，调用唤醒方法，去修改已注册了的 其他被观察者的状态
    public void setStatus(int status1, int status2, String message) {
        this.status1 = status1;
        this.status2 = status2;
        // 通知(分发)观察者，更新其他 观察者的状态,将消息推送给所有的观察者
        notifyObservers(message);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.contains(observer))
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer: observers) {
            observer.update(this.status1, this.status2, message, this);
        }
    }

    @Override
    public String clientUpgrade_pull() {
        System.out.println("客户端正在升级......");
        return "客户端升级成功";
    }
}
