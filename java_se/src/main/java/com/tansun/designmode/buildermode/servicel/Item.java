package com.tansun.designmode.buildermode.servicel;

import java.math.BigDecimal;

/**
 * @author 吴槐
 * @version V1.0
 * @ClassName Item
 * @Description TODO
 * @date 2022/9/21 16:00
 * @Copyright © 2020 阿里巴巴
 */
public interface Item {
    public String name();
    public Packing packing();
    public void setItemNumber(Integer num);
    public Integer getItemNumber();
    public BigDecimal price();
}
