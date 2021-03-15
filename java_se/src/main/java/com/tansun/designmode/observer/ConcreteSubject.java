package com.tansun.designmode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听器
 *      提供 被监听者的集合
 *      提供 被监听者的状态
 *      提供 被监听者的注册方法
 *      提供 被监听者的移除方法
 *      提供 被监听者的唤醒方法
 */
public class ConcreteSubject implements Subject{

    // 监听的状态
    private int status1;
    // 监听的状态
    private int status2;

    // 观察者集合
    private List<Observer> observers = new ArrayList<Observer>();

    // 当观察者（监听器）状态放生改变时，调用唤醒方法，去修改已注册了的 被观察者的状态
    public void setStatus(int status1, int status2) {
        this.status1 = status1;
        this.status2 = status2;
        notifyObservers();
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
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update(this.status1, this.status2);
        }
    }
}
