package com.tansun.designmode.factory;
/**
 * @ClassName ShapeFactory
 * @Description 工厂模式--形状工厂
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 15:51
 * @Copyright © 2020 阿里巴巴
 */
public class ShapeFactory {

    //使用 getShape 方法获取形状类型的对象
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
