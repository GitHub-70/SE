package com.tansun.designmode.observer.pull;

/**
 * 观察者模式（拉模型）
 *
 * 被观察者接口
 *      注册方法
 *      删除方法
 *      唤醒/通知方法
 */
public interface Subject {

    // 注册方法
    public void registerObserver(Observer o);
    // 删除方法
    public void removeObserver(Observer o);
    // 唤醒/通知方法
    public void notifyObservers(String message);

    // 基于事件的回调函数
    public String clientUpgrade_pull();

}
