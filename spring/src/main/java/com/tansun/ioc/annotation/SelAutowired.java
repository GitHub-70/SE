package com.tansun.ioc.annotation;/**
 * @Description TODO
 * @ClassName SelService
 * @author 吴槐
 * @date 2020/11/3 15:34
 * @version V1.0
 * @Copyright © 2020 阿里巴巴
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 模拟Autowired注解
 * @ClassName SelService
 * @author 吴槐
 * @date 2020/11/3 15:34
 * @version V1.0
 * @Copyright © 2020 阿里巴巴
 */
@Target({ElementType.FIELD, ElementType.METHOD})//描述此注解可作用在方法或属性上
@Retention(RetentionPolicy.RUNTIME)
public @interface SelAutowired {

}
