package com.tansun.designmode.factory;
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
