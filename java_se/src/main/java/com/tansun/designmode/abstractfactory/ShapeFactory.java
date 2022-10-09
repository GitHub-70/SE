package com.tansun.designmode.abstractfactory;

import com.tansun.designmode.abstractfactory.service.Color;
import com.tansun.designmode.factory.Circle;
import com.tansun.designmode.factory.Rectangle;
import com.tansun.designmode.factory.Shape;
import com.tansun.designmode.factory.Square;

/**
 * @ClassName ShapeFactory
 * @Description 工厂模式--形状工厂
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 15:51
 * @Copyright © 2020 阿里巴巴
 */
public class ShapeFactory extends AbstractFactory{

    @Override
    public Color getColor(String color) {
        return null;
    }

    //使用 getShape 方法获取形状类型的对象
    @Override
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
