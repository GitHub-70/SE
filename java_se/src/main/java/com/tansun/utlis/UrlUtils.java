package com.tansun.utlis;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName UrlUtils
 * @date 2020/11/14 3:04
 * @Copyright © 2020 阿里巴巴
 */

public class UrlUtils {
    private static String url = "E:/文本文件/Linux常用命令.txt";
    private static String url1 = "E:/xjpic.jpg";
    private static String inputUrl = "E:/file/img";

    // 让构造函数私有化，这样该类不会被实例化
    private UrlUtils() {
    }

    public static String getUrl(){
        return url;
    }

    public static String getInputUrls() {
        // 获取时间戳字符串
        String dateString = DateUtils.getDateString();
        String fileName = url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));
        String fileNameSuf = url.substring(url.lastIndexOf("."));
        String inputUrlFinal = inputUrl + "/" + fileName + dateString + fileNameSuf;
        return inputUrlFinal;
    }
}
