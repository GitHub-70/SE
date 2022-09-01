package com.tansun.designmode.observer.pull;

/**
 * 观察者接口
 *      提供 更改 观察者的状态
 */
public interface Observer {

    public void update(int stastus1, int stastus2, String message, Subject observable);
}
