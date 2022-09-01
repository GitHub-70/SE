package com.tansun.utlis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 吴槐
 * @version V1.0
 * @Description 时间工具类
 * @ClassName DateUtlis
 * @date 2020/11/9 15:25
 * @Copyright © 2020 阿里巴巴
 */
public class DateUtils {

    // DCL 单例对象必须volatile 防止指令重排序，拿到未初始化对象，指令重排序可以充分利用CPU的性能
    private volatile static Date date;

    // 让构造函数私有化，这样该类不会被实例化
    private DateUtils() {
    }

    /**
     * 用于时分秒命名 时间格式
     * @return
     */
    public static String getTimeString() {
        // date 是否要用该类的变量？ TODO
        date = getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }

    /**
     * 用于获取完整时间格式
     * @return
     */
    public static String getDateTimeString(){
        date = getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyHHmmss");
        String dateTime = simpleDateFormat.format(date);
        return dateTime;
    }

    /**
     * 用于获取完整时间格式
     * @return
     */
    public static String getDateTime(){
        date = getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String dateTime = simpleDateFormat.format(date);
        return dateTime;
    }

    /**
     * 用于获取日期格式
     * @return
     */
    public static String getDateYear(){
        date = getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String dateTime = simpleDateFormat.format(date);
        return dateTime;
    }
    /**
     * 用于获取时间格式
     * @return
     */
    public static String getTime(){
        date = getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String dateTime = simpleDateFormat.format(date);
        return dateTime;
    }

    // 获取唯一可用的对象
    // 双检锁/双重校验锁
    private static Date getDate() {
        if (null == date) {
            synchronized (Date.class) {
                if (null == date) {
                    date = new Date();
                }
            }
        }
        return date;
    }
}
