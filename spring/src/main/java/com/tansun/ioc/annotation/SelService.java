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
 * @Description 注解服务类
 * @ClassName SelService
 * @author 吴槐
 * @date 2020/11/3 15:34
 * @version V1.0
 * @Copyright © 2020 阿里巴巴
 */
@Target(ElementType.TYPE)//描述此注解可作用在类 接口 枚举上
@Retention(RetentionPolicy.RUNTIME)
public @interface SelService {

}
