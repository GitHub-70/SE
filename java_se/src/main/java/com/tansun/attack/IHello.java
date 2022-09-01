package com.tansun.attack;

import java.rmi.Remote;

/**
 *  IHello是客户端和服务端共用的接口（客户端本地必须有远程对象的接口，
 *      不然无法指定要调用的方法，而且其全限定名必须与服务器上的对象完全相同）
 *
 *  该接口必须继承Remote接口，不然客户端调用目标方法，Remote类型不能转换为IHello
 */
public interface IHello extends Remote {

    public String sayHello(String name);
}
