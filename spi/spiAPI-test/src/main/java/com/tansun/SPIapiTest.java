package com.tansun;
/**
 * 通过SPI机制 测试peopleAPI
 * 依赖相关的接口  如：jdbc                      SLF4J
 * 依赖具体的实现  如：mysql驱动/oracle驱动      log4j/logback
 */

import com.tansun.spi.PeopleFactory;

import java.security.cert.Extension;

public class SPIapiTest {

    public static void main(String[] args) {
        PeopleFactory peopleFactory = new PeopleFactory();
        peopleFactory.invoke();

    }
    
}
