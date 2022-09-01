package com.tansun.ioc.annotation;

import com.tansun.classloader.UserClassLoader;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.tansun.common.SpringConfiguration;

import java.util.Locale;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName Test
 * @date 2020/11/12 13:01
 * @Copyright © 2020 阿里巴巴
 */

public class SpringBean implements BeanPostProcessor {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        UserClassLoader userClassLoader = (UserClassLoader)annotationConfigApplicationContext.getBean("userClassLoader");

        String message = annotationConfigApplicationContext.getMessage("msg.valid.common.success", null, Locale.CHINA);
        System.out.println(message);

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("");

    }

}
