package com.tansun.spi;

public interface People {

    // 定义了一个返回值为泛型的方法
    <T extends Object>T getPeopleType();

    <T>T getPeople();


}
