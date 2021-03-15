package com.tansun.ioc.aop.impl;


import com.tansun.ioc.aop.intefaces.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description TODO
 * @ClassName SubjectImpl
 * @author 吴槐
 * @date 2020/11/6 11:58
 * @version V1.0
 * @Copyright © 2020 阿里巴巴
 */
public class SubjectImpl implements Subject {

    private static Logger logger = LoggerFactory.getLogger(Subject.class);
    @Override
    public String request(String args) {
        logger.info("调用了目标对象SubjectImpl的request()方法");
        return args;
    }

    @Override
    public void run() {
        logger.info("调用了目标对象SubjectImpl的run()方法");
    }
}
