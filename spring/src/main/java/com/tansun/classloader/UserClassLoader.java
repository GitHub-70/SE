package com.tansun.classloader;

import org.springframework.stereotype.Component;

/**
 * @author 吴槐
 * @version V1.0
 * @Description 自定义类加载器
 * @ClassName UserClassLoader
 * @date 2020/11/8 14:09
 * @Copyright © 2020 阿里巴巴
 */
@Component
public class UserClassLoader extends ClassLoader{
    public UserClassLoader(){
        System.out.println("UserClassLoader已经被创建了");
    }

}
