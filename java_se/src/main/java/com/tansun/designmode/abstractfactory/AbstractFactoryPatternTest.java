package com.tansun.designmode.abstractfactory;

import com.tansun.designmode.abstractfactory.service.Color;
import com.tansun.designmode.factory.Shape;

/**
 * @ClassName AbstractFactoryPatternTest
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 16:42
 * @Copyright © 2020 阿里巴巴
 */
public class AbstractFactoryPatternTest {

    public static void main(String[] args) {
        //获取形状工厂
        AbstractFactory shapeFactory = new ShapeFactory();


        //获取形状为 Circle 的对象
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //调用 Circle 的 draw 方法
        shape1.doDraw();

        //获取形状为 Rectangle 的对象
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //调用 Rectangle 的 draw 方法
        shape2.doDraw();

        //获取形状为 Square 的对象
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //调用 Square 的 draw 方法
        shape3.doDraw();

        //获取颜色工厂
        AbstractFactory colorFactory = new ColorFactory();

        //获取颜色为 Red 的对象
        Color color1 = colorFactory.getColor("RED");

        //调用 Red 的 fill 方法
        color1.fill();

        //获取颜色为 Green 的对象
        Color color2 = colorFactory.getColor("GREEN");

        //调用 Green 的 fill 方法
        color2.fill();

        //获取颜色为 Blue 的对象
        Color color3 = colorFactory.getColor("BLUE");

        //调用 Blue 的 fill 方法
        color3.fill();
    }
}
