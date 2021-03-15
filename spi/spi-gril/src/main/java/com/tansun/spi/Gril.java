package com.tansun.spi;

public class Gril implements People {
    @Override
    public <T> T getPeopleType() {
        String gril = "我是某一类型的女孩";
        return (T)gril;
    }

    @Override
    public <T> T getPeople() {
        String gril = "我是个女孩";
        return (T)gril;
    }
}
