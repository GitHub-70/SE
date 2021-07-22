package com.tansun.spi.spring.bean;

import com.tansun.pool.ThreadPoolExecutors;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName SpringBean
 * @date 2020/11/14 4:06
 * @Copyright © 2020 阿里巴巴
 */
public class SpringBean {

    @Test
    public void getBean() {
//        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(ThreadPoolExecutors.class);
        ThreadPoolExecutors urlConfiguration = (ThreadPoolExecutors) acac.getBean("threadPoolExecutors");

        System.out.println("ThreadPoolExecutors:"+urlConfiguration.toString());

    }
}
