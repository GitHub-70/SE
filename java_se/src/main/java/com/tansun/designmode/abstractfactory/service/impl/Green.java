package com.tansun.designmode.abstractfactory.service.impl;

import com.tansun.designmode.abstractfactory.service.Color;

/**
 * @ClassName Green
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 16:08
 * @Copyright © 2020 阿里巴巴
 */
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
