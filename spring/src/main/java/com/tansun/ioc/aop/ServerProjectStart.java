package com.tansun.ioc.aop;

import com.tansun.ioc.aop.dynamic.proxy.DynamicProxyObject;
import com.tansun.ioc.aop.impl.SubjectImpl;
import com.tansun.ioc.aop.intefaces.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @Description 模拟项目启动spring如何获取信息
 * @ClassName ServerProjectStart
 * @author 吴槐
 * @date 2020/11/6 12:41
 * @version V1.0
 * @Copyright © 2020 阿里巴巴
 */
public class ServerProjectStart {

    private static Logger logger = LoggerFactory.getLogger(ServerProjectStart.class);

    public static void main(String[] args) {
        //模拟将Subject对象交个spring管理，spring创建此对象
        Subject subject = new SubjectImpl();
        InvocationHandler  invocationHandler = (DynamicProxyObject)new DynamicProxyObject(subject);
        //获取目标对象的字节码对象
        Class<?> subjectClass = subject.getClass();

        //利用Proxy创建代理目标对象的实例
        Subject subjectProxy = (Subject)Proxy.newProxyInstance(subjectClass.getClassLoader(), subjectClass.getInterfaces(), invocationHandler);

        //此代理目标对象的实例subjectProxy 与代理对象Proxy的关系
        if (subjectProxy instanceof Proxy)
            logger.info("{}", subjectProxy instanceof Proxy);
        logger.info("subjectProxy代理目标对象是Proxy代理对象的实例，即目标接口Subject有两个实例对象，分别是{}，{}", subject.getClass(),subjectProxy.getClass());

        // 通过代理目标对象的实例调用目标对象的方法
        // 调用目标对象的方法进入到了DynamicProxyObject的invoke方法中
        String string = subjectProxy.request("abcd");
        logger.info("通过代理对象 调用目标对象的request方法,返回值为==={}",string);
        subjectProxy.run();

        logger.info("验证代理对象是否实现了 目标对象的接口");
        logger.info("\n");
        logger.info("{}",subjectProxy instanceof Subject);
        Class<?> subjectProxyClass = subjectProxy.getClass();
        Method[] methods = subjectProxyClass.getDeclaredMethods();
        for (Method method : methods) {
            String subjectProxyMethod = method.getName();
            logger.info("代理对象中的方法==={}", subjectProxyMethod);
        }
    }

}
