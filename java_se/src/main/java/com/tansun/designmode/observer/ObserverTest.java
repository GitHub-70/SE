package com.tansun.designmode.observer;

public class ObserverTest {
    public static void main(String[] args) {
        // 监听器
        ConcreteSubject concreteSubject = new ConcreteSubject();

        // 模拟被观察者状态放生改变
        Observer1 observer1 = new Observer1();
        Observer2 observer2 = new Observer2();

        // 将被观察者 注册到监听器中
        concreteSubject.registerObserver(observer1);
        concreteSubject.registerObserver(observer2);

        // 监听器中 监听的状态发生改变 修改被观察者的状态
        concreteSubject.setStatus(11, 22);

        System.out.println("---------------------分割线----------------------");
        // 移除被观察者1
        concreteSubject.removeObserver(observer1);
        concreteSubject.setStatus(11, 22);



    }
}
