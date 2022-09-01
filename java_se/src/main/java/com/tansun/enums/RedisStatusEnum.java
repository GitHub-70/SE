package com.tansun.enums;

public enum RedisStatusEnum {

    /**
     * 未赋值
     */
    UNDEFIND("0","未赋值"),
    /**
     * 正常
     */
    NORMAL("1","正常"),
    /**
     * 非正常
     */
    UNNORMAL("-1","非正常");

    /**
     * key
     */
    private String code;
    /**
     * value
     */
    private String status;

    RedisStatusEnum(String code, String status) {
        this.code = code;
        this.status= status;
    }

    /**
     * 通过code获取status
     */

    public static String getStatusByCode(String code){
        for(RedisStatusEnum redisStatusEnum : RedisStatusEnum.values()){
            if (redisStatusEnum.code.equals(code)){
                return redisStatusEnum.status;
            }
        }
        return "";
    }
}
