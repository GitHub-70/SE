package com.tansun.annotation.validateannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamsCheck {

    /* *
     * @Author 吴槐
     * @Description 校验值默认为true
     * @Date 9:59 2022/9/9
     * @Param 
     * @return boolean
     *       
     **/
    boolean require() default true;
	
}
