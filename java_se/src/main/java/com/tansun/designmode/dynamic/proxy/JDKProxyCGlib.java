package com.tansun.designmode.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* *
 * @Author 吴槐
 * @Description
 * @Date 8:46 2022/7/13
 * @Param null
 * @return
 *
 * JDK的动态代理
 * CGLIB的动态代理
 *   二者的区别是JDK动态代理是通过实现接口的方式(代理的对象为接口)，
 *   因此只能代理接口中的方法。
 *   而CGLIB动态代理是通过继承的方式，因此可以对对象中的方法进行代理，但是由于是继承关系，
 *   无法代理final的类和方法(无法继承)，或是private的方法(对子类不可见)。
 *
 * 更多详细及代理过程：https://zhuanlan.zhihu.com/p/228451195
 *
 **/
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
