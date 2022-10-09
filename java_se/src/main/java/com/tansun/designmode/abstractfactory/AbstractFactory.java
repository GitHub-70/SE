package com.tansun.designmode.abstractfactory;

import com.tansun.designmode.abstractfactory.service.Color;
import com.tansun.designmode.factory.Shape;

/**
 * @ClassName AbstractFactory
 * @Description 抽象工厂
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 16:09
 * @Copyright © 2020 阿里巴巴
 */
public abstract class AbstractFactory {

    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}
