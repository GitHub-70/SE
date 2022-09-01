package com.tansun.enums;

public enum AnimalEnum {

    /**
     * 未赋值
     */
    DOG("狗"),
    /**
     * 正常
     */
    CAT("猫");

    /**
     * desc
     */
    private String desc;

    AnimalEnum(String desc) {
        this.desc = desc;
    }

    /**
     * 通过name获取desc
     */

    public static String getDescByName(String name){
        for(AnimalEnum animalEnum : AnimalEnum.values()){
            if (animalEnum.name().equals(name)){
                return animalEnum.desc;
            }
        }
        return "";
    }
}
