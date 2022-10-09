package com.tansun.designmode.buildermode.servicel.abs;

import com.tansun.designmode.buildermode.Bottle;
import com.tansun.designmode.buildermode.servicel.Item;
import com.tansun.designmode.buildermode.servicel.Packing;

import java.math.BigDecimal;

/**
 * @ClassName ColdDrink
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/21 16:09
 * @Copyright © 2020 阿里巴巴
 */
public abstract class ColdDrink implements Item {

    private Integer itemNum;

    @Override
    public Packing packing() {
        return new Bottle();
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
