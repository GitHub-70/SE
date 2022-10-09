package com.tansun.designmode.buildermode.servicel.impl;

import com.tansun.designmode.buildermode.servicel.abs.ColdDrink;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @ClassName Pepsi
 * @Description 百事可乐
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/21 16:24
 * @Copyright © 2020 阿里巴巴
 */
public class Pepsi extends ColdDrink {

    @Override
    public BigDecimal price() {
        return new BigDecimal(35.0);
    }

    @Override
    public String name() {
        return "Pepsi";
    }

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
