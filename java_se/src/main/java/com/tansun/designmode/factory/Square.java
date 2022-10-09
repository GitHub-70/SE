package com.tansun.designmode.factory;
/**
 * @ClassName Square
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 15:58
 * @Copyright © 2020 阿里巴巴
 */
public class Square implements Shape {
    @Override
    public void doDraw() {
        System.out.println("Inside Square::doDraw() method.");
    }
}
