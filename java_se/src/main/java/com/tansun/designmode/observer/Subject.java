package com.tansun.designmode.observer;

/**
 * 观察者模式
 *
 * 注册方法
 * 删除方法
 * 唤醒/通知方法
 */
public interface Subject {

    // 注册方法
    public void registerObserver(Observer o);
    // 删除方法
    public void removeObserver(Observer o);
    // 唤醒/通知方法
    public void notifyObservers();

}
