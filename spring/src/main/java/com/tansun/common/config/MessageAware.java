package com.tansun.common.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.List;

/**
 * 解析通配符 形式的配置文件地址，依次找到符合条件的配置文件
 * 将它们读取到环境中
 */

public class MessageAware implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            Resource[] resources = resolver.getResources("classpath*:message*.properties");
            for (Resource resource : resources) {
                PropertiesPropertySourceLoader propertySourceLoader = new PropertiesPropertySourceLoader();
                List<PropertySource<?>> propertySources;
                propertySources = propertySourceLoader.load(resource.getFilename(), resource);
                if (null != propertySources && !propertySources.isEmpty()){
                    propertySources.stream().forEach(environment.getPropertySources()::addLast);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
