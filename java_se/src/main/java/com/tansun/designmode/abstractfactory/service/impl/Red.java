package com.tansun.designmode.abstractfactory.service.impl;

import com.tansun.designmode.abstractfactory.service.Color;

/**
 * @ClassName Red
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 16:07
 * @Copyright © 2020 阿里巴巴
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
