package com.tansun.designmode.abstractfactory.service.impl;

import com.tansun.designmode.factory.Shape;

/**
 * @ClassName Rectangle
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 15:57
 * @Copyright © 2020 阿里巴巴
 */
public class Rectangle implements Shape {
    @Override
    public void doDraw() {
        System.out.println("Inside Rectangle::doDraw() method.");
    }
}
