package com.tansun.designmode.dynamic.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 利用的是spring的核心包 中的 MethodInterceptor
 */
public class CGlibReproxy implements MethodInterceptor {

    // 根据一个类型 产生代理类
    public Object creatProxyInstance(Class<?> clazz ){
        // 获取一个字节码 增强器
        Enhancer enhancer = new Enhancer();
        // 设置
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {


        System.out.println("执行目标方法之前===项目部署,对原功能进行的再次扩展，增加该代理类");
        // 调用目标方法
        methodProxy.invokeSuper(o,objects);

        System.out.println("执行目标方法之后===项目部署,对原功能进行的再次扩展，增加该代理类");

        return null;
    }
}
