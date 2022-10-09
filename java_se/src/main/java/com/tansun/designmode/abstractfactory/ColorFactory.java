package com.tansun.designmode.abstractfactory;

import com.tansun.designmode.abstractfactory.service.Color;
import com.tansun.designmode.abstractfactory.service.impl.Blue;
import com.tansun.designmode.abstractfactory.service.impl.Green;
import com.tansun.designmode.abstractfactory.service.impl.Red;
import com.tansun.designmode.factory.Shape;

/**
 * @ClassName ColorFactory
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/16 16:36
 * @Copyright © 2020 阿里巴巴
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
