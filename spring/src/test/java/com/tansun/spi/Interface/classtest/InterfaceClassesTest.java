package com.tansun.spi.Interface.classtest;

import com.tansun.ioc.aop.intefaces.Subject;


/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName InterfaceClasess
 * @date 2020/11/7 4:27
 * @Copyright © 2020 阿里巴巴
 */
public class InterfaceClassesTest {
    public static void main(String[] args) {

        Class<?>[] classs = { Subject.class };

        for (Class<?> aClass : classs) {
            System.out.println(aClass.getClassLoader());
            System.out.println(aClass.getClass().getName());

        }
    }
}
