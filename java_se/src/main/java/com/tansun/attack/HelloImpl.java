package com.tansun.attack;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * HelloImpl是一个服务端远程对象，提供了一个sayHello方法供远程调用。
 *      它没有继承UnicastRemoteObject类或者实现java.rmi.Remote接口，
 *      而是在构造方法中调用了UnicastRemoteObject.exportObject()。
 *
 * 在JVM之间通信时，RMI对远程对象和非远程对象的处理方式是不一样的
 *      客户端通过 远程对象的引用或者代理对象Stub 调用本地方法一样，
 *      直接通过它来调用远程方法，Stub对开发者是透明的。Stub中包含了
 *      远程对象的定位信息，如Socket端口、服务端主机地址等等。
 *
 * 调用过程：
 *      Server端监听一个端口，这个端口是JVM随机选择的；
 *      Client端并不知道Server远程对象的通信地址和端口，但是Stub中包含了这些信息，并封装了底层网络操作；
 *      Client端可以调用Stub上的方法；
 *      Stub连接到Server端监听的通信端口并提交参数；
 *      远程Server端上执行具体的方法，并返回结果给Stub；
 *      Stub返回执行结果给Client端，从Client看来就好像是Stub在本地执行了这个方法一样；
 *
 *  那怎么获取Stub呢？
 *      RMI注册表。JDK提供了一个RMI注册表（RMIRegistry）来解决这个问题。
 *      RMIRegistry也是一个远程对象，默认监听在传说中的1099端口上，
 *
 */
public class HelloImpl implements IHello, Serializable {

    protected HelloImpl() throws RemoteException {
        super();
        // 底层调用的是 导出对象 方法
//        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public String sayHello(String name) {
        System.out.println(name);
        return name;
    }
}