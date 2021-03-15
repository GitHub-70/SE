package com.tansun.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName UrlConfiguration
 * @date 2020/11/14 17:19
 * @Copyright © 2020 阿里巴巴
 */
@Configuration// 此注解需要public、protected级别的构造函数,才能创建bean
//@Component // 此注解对private级别的构造函数也能创建bean
@PropertySource(value = "classpath:/properties/url.properties")
public class UrlConfiguration {
    @Value("${file.url1}")      //  @Value无法为静态属性赋值
    private static String url1;
    @Value("${file.url}")       //  @Value需要提供get/set方法
    private String url;
    @Value("${file.inputUrl}")  //  @Value需要配合@Configuration注解才能进行跳转--Ctrl+点击
    private String inputUrl;

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInputUrl() {
        return inputUrl;
    }

    public void setInputUrl(String inputUrl) {
        this.inputUrl = inputUrl;
    }

    @Override
    public String toString() {
        return "UrlConfiguration{" +
                "url1=" + url1 +
                ", url=" + url +
                ", inputUrl=" + inputUrl+"}";
    }
}
