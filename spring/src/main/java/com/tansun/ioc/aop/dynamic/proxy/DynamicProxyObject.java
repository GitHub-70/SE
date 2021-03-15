package com.tansun.ioc.aop.dynamic.proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description 模拟底层的动态代理类  方法调用处理器
 *              此类有点像@Aspect注解描述的类
 * @ClassName DynamicProxyObject
 * @author 吴槐
 * @date 2020/11/6 12:26
 * @version V1.0
 * @Copyright © 2020 阿里巴巴
 */
public class DynamicProxyObject implements InvocationHandler {

    private static Logger logger = LoggerFactory.getLogger(DynamicProxyObject.class);

    private Object object;

    public DynamicProxyObject (){

    }

     public DynamicProxyObject (Object object){
        this.object = object;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //在代理对象调用真实对象的方法前，添加操作
        logger.info("代理对象 调用 目标对象方法前的操作，传进来的对象==={}\n其参数=={}", object.getClass(),args);

        // 反射中的Method对象
        /** 通过此方法调用了目标对象的方法 */
        method.invoke(object,args);

        /**此方法为递归，自身无限调用自身*/
//        method.invoke(proxy, args);//

        //在代理对象调用真实对象的方法后，添加操作
        logger.info("代理对象 调用 目标对象方法后的操作，执行了method.invoke(object,args)方法");

        return null;
    }
}
