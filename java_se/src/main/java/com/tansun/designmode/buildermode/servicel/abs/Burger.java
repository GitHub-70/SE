package com.tansun.designmode.buildermode.servicel.abs;

import com.tansun.designmode.buildermode.CartonWrapper;
import com.tansun.designmode.buildermode.servicel.Item;
import com.tansun.designmode.buildermode.servicel.Packing;

import java.math.BigDecimal;

/**
 * @ClassName Burger
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/21 16:05
 * @Copyright © 2020 阿里巴巴
 */
public abstract class Burger implements Item {
    private Integer itemNum;

    @Override
    public Packing packing(){
        return new CartonWrapper();
    }

    @Override
    public void setItemNumber(Integer num) {
        itemNum = num;
    }

    @Override
    public Integer getItemNumber() {
        return itemNum;
    }

    @Override
    public abstract BigDecimal price();
}
