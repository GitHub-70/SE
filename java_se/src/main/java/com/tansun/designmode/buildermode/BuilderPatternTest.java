package com.tansun.designmode.buildermode;

import com.tansun.designmode.buildermode.servicel.meal.OrderingFood;
import com.tansun.designmode.buildermode.servicel.meal.OrderingFoodBuilder;

/**
 * @ClassName BuilderPatternTest
 * @Description 建造者模式
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 9:05
 * @Copyright © 2020 阿里巴巴
 */
public class BuilderPatternTest {
    public static void main(String[] args) {
        OrderingFoodBuilder mealBuilder = new OrderingFoodBuilder();

        OrderingFood vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());

        OrderingFood nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());
    }
}
