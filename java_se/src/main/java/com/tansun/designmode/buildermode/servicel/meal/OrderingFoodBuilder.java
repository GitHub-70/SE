package com.tansun.designmode.buildermode.servicel.meal;

import com.tansun.designmode.buildermode.servicel.impl.ChickenBurger;
import com.tansun.designmode.buildermode.servicel.impl.Coke;
import com.tansun.designmode.buildermode.servicel.impl.Pepsi;
import com.tansun.designmode.buildermode.servicel.impl.VegBurger;

/**
 * @ClassName OrderingFoodBuilder
 * @Description 不同套餐
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 9:00
 * @Copyright © 2020 阿里巴巴
 */
public class OrderingFoodBuilder {

    public OrderingFood prepareVegMeal (){
        OrderingFood meal = new OrderingFood();
        meal.addItem(new VegBurger(),2);
        meal.addItem(new Coke(),2);
        meal.addItem(new Coke(),1);
        meal.addItem(new VegBurger(),1);
        return meal;
    }

    public OrderingFood prepareNonVegMeal (){
        OrderingFood meal = new OrderingFood();
        meal.addItem(new ChickenBurger(),2);
        meal.addItem(new Pepsi(), 1);
        return meal;
    }
}
