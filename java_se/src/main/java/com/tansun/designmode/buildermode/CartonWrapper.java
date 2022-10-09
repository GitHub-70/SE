package com.tansun.designmode.buildermode;

import com.tansun.designmode.buildermode.servicel.Packing;

/**
 * @ClassName Wrapper
 * @Description 纸盒包装
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/21 16:03
 * @Copyright © 2020 阿里巴巴
 */
public class CartonWrapper implements Packing {

    @Override
    public String pack() {
        return "打包方式：CartonWrapper";
    }
}
