package com.tansun.utlis;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeUtils {

    // 提供私有构造函数 让其不备实例化
    private LocalTimeUtils(){}
    private static  LocalTime localTime;

    //
    public static LocalTime getLocalTime(int hour, int minute){
        if (null == localTime){
            synchronized (LocalTime.class){
                if (null == localTime)
                    localTime = LocalTime.of(hour, minute);
            }
        }
        return localTime;
    }

    // 转换时间格式
    public static LocalTime convertTimeFormat(LocalTime localTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        // 将具体的日期类型 转换为String
        String localTimeString = localTime.format(dateTimeFormatter);
        // 将String类型的日期转换 为日期
        LocalTime localTimeFomat = LocalTime.parse(localTimeString);
        return localTimeFomat;
    }

    public static void main(String[] args) {
        LocalTime localTime = LocalTimeUtils.getLocalTime(15, 22);
        LocalTime currentTime = LocalTime.now();
        LocalTime currentTimeFormat = LocalTimeUtils.convertTimeFormat(currentTime);
        System.out.println(currentTimeFormat);
        if (localTime.equals(currentTimeFormat)){
            System.out.println("当前时间为 =="+currentTimeFormat);
        }
    }
}
