package com.tansun.ioc.aop.intefaces;

/**
 * @Description TODO
 * @interface Subject
 * @author 吴槐
 * @date 2020/11/6 11:38
 * @version V1.0
 * @Copyright © 2020 阿里巴巴
 */
public interface Subject {

    public String request(String args);

    void run();
}
