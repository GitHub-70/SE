package com.tansun.designmode.abstractfactory.service.impl;

import com.tansun.designmode.factory.Shape;

/**
 * @ClassName Circle
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 15:55
 * @Copyright © 2020 阿里巴巴
 */
public class Circle implements Shape {

    @Override
    public void doDraw() {
        System.out.println("Inside Circle::doDraw() method.");
    }
}
