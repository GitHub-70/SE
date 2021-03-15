package com.tansun.designmode.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    private Object object;

    public JDKProxy(Object object){
        this.object = object;
    };

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("目标方法执行之前=====，项目部署,对原功能进行扩展，增加该代理类");

        Object targetResult = method.invoke(object);

        System.out.println("目标方法执行之后=====，项目部署,对原功能进行扩展，增加该代理类");

        return targetResult;
    }

    // 利用反射创建代理对象的实例
    public Object creatProxyInstance(){
        Object proxyInstance = Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
        return proxyInstance;
    }
}
