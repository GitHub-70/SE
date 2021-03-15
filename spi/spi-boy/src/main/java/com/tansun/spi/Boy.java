package com.tansun.spi;

import com.tansun.spi.People;

public class Boy implements People {

    @Override
    public <T> T getPeopleType() {
        String boy = "我是某一类型的男孩";
        return (T) boy;
    }

    @Override
    public <T> T getPeople() {
        String boy = "我是个男孩";
        return (T) boy;
    }
}
