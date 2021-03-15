package com.tansun.interfaces.impl;

import com.tansun.interfaces.SubjectInterface;


public  class RentImpl implements SubjectInterface {


    @Override
    public String rent() {

        System.out.println("是否需要出租");

        return "合格就出租";
    }

    @Override
    public Integer sell() {

        System.out.println("出售需要多少钱");

        return 500;
    }
}
