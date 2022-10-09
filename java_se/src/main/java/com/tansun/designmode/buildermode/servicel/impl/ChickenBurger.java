package com.tansun.designmode.buildermode.servicel.impl;

import com.tansun.designmode.buildermode.servicel.abs.Burger;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @ClassName ChickenBurger
 * @Description 鸡公汉堡
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/21 16:12
 * @Copyright © 2020 阿里巴巴
 */
public class ChickenBurger extends Burger {

    @Override
    public BigDecimal price() {
        return new BigDecimal(50.5);
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }


    /* *
     * @Author 吴槐
     * @Description 为什么重写equals方法，还必须要重写hashcode方法
     *  https://blog.csdn.net/wdy00000/article/details/124439846
     * @Date 9:45 2022/9/22
     * @Param obj
     * @return boolean
     *
     * 重写后两个方法的关系：
     *  equals()相等的两个对象，hashcode()一定相等；
     *  hashcode()不等，一定能推出equals()也不等；
     *  hashcode()相等，equals()可能相等，也可能不等。
     *  所以先进行hashcode（）判断，不等就不用equals（）方法了。
     *  但equels是是根据对象的特征进行重写的，有时候特征相同，但hash值不同，
     *  也不是一个对象。 所以两个都重写才能保障是同一个对象。
     *
     **/


    @Override
    public boolean equals(Object obj) {
        // 如果是同一个对象(地址相等)直接返回true，提高效率
        if (this == obj){
            return true;
        }
        // 使用getClass()判断对象是否属于该类
        if (null == obj || this.getClass() != obj.getClass()){
            return false;
        }
        // 由于该对象没有任何属性，只需判断是同一个类
        if (this.getClass() == obj.getClass()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name(), this.price());
    }
}
