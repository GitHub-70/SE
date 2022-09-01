package com.tansun.designmode.observer.push;

// https://www.cnblogs.com/jackson-yqj/p/7784694.html

public class ConcreteSubjectTest {
    public static void main(String[] args) {
        // 被观察者
        ConcreteSubject concreteSubject = new ConcreteSubject();

        // 观察者
        Observer1 observer1 = new Observer1();
        Observer2 observer2 = new Observer2();

        // （观察者1、2）注册到 被观察者中
        concreteSubject.registerObserver(observer1);
        concreteSubject.registerObserver(observer2);

        // 模拟 被观察者 的状态发生改变  查看 观察者1，2的状态
        concreteSubject.setStatus(11, 23, "系统正在维护");

        System.out.println("---------------------分割线,观察者1取消订阅后----------------------");
        // 观察者可以自己决定是否要订阅 被观察者 的通知；
        concreteSubject.removeObserver(observer1);
        concreteSubject.setStatus(11, 23, "系统正在维护");


    }
}
