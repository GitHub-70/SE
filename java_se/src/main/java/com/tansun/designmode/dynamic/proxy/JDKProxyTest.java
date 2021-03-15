package com.tansun.designmode.dynamic.proxy;

import com.tansun.interfaces.SubjectInterface;
import com.tansun.interfaces.impl.RentImpl;
import org.springframework.cglib.proxy.MethodInterceptor;

public class JDKProxyTest {

    public static void main(String[] args) throws Throwable {
        jdkProxyTest();
//        jdkProxyCGlibTest();
    }

    private static void jdkProxyTest(){
        // 模拟被代理的对象 交给spring管理，获取目标对象
        SubjectInterface rent =  new RentImpl();
        // 获取含有目标对象的代理对象 处理器
        JDKProxy jdkProxy = new JDKProxy(rent);
        // 利用反射创建代理类的实例
        SubjectInterface subjectInterface = (SubjectInterface)jdkProxy.creatProxyInstance();

        // 通过代理对象的实例 条用目标方法
        subjectInterface.rent();
    }

    private static void jdkProxyCGlibTest() throws Throwable {
        // 模拟被代理的对象 交给spring管理，获取目标对象
        MethodInterceptor cGlibReproxy =  new CGlibReproxy();
        // 获取含有目标对象的代理对象 处理器
        JDKProxyCGlib jdkProxyCGlib = new JDKProxyCGlib(cGlibReproxy);
        // 利用反射创建代理类的实例
        MethodInterceptor jdkProxyInstance = (MethodInterceptor)jdkProxyCGlib.getJDKProxyInstance();

        // 通过代理对象的实例 条用目标方法
        jdkProxyInstance.intercept(cGlibReproxy, null, null ,null);
    }
}
