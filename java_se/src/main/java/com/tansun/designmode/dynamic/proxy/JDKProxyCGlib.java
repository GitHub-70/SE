package com.tansun.designmode.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyCGlib implements InvocationHandler {

    private Object object;

    JDKProxyCGlib(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("通过JKD代理增来强cglib代理--前的处理逻辑");

        method.invoke(object);

        System.out.println("通过JKD代理增来强cglib代理--后的处理逻辑");
        return null;
    }

    // 通过Porxy反射类 来创建目标对象的jdk代理实例
    protected Object getJDKProxyInstance(){
        Object proxyInstance = Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
        return proxyInstance;
    }
}
