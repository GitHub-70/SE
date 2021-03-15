package com.tansun.designmode.dynamic.proxy;

import com.tansun.interfaces.impl.RentImpl;

public class CGlibProxyTest {

    public static void main(String[] args) throws InterruptedException {
        rentImplProxy();
//        cglibProxyProxy();
    }

    private static void rentImplProxy() {
        // 创建被代理对象
        RentImpl rent = new RentImpl();
        // 创建CGLIB代理对象
        CGlibProxy cGlibProxy = new CGlibProxy();
        // 利用增强器 创建目标类的代理对象的实例
        RentImpl rentImpl = (RentImpl)cGlibProxy.creatProxyInstance(rent.getClass());

        // 通过代理对象实例调用目标方法
        rentImpl.rent();
    }

    private static void cglibProxyProxy() {

        RentImpl rent = new RentImpl();
        // 创建被代理对象
        CGlibProxy cGlibProxy = new CGlibProxy();
        // 创建CGLIB代理对象
        CGlibReproxy cGlibReproxy = new CGlibReproxy();
        // 利用增强器 创建目标类的代理对象的实例
        CGlibProxy cGlibProxyInstance = (CGlibProxy)cGlibReproxy.creatProxyInstance(cGlibProxy.getClass());

        RentImpl rent1 = (RentImpl)cGlibProxyInstance.creatProxyInstance(rent.getClass());
        // 通过代理对象实例调用目标方法
        rent1.rent();
    }

}
