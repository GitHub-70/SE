package com.tansun.nio.properties;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName PropertiesTest
 * @date 2020/11/11 14:45
 * @Copyright © 2020 阿里巴巴
 */


import com.tansun.utlis.DateUtils;
import org.apache.ibatis.parsing.PropertyParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * java.util.properties
 * properties类，表示了一个持久的属性集合。extends HashTable implements Map
 * properties集合，是唯一一个和IO相结合的集合
 * store()方法,把集合中的数据，持久化到硬盘中存储
 * load()方法，把硬盘中的数据，读取到集合中
 * 其key和value都是字符串
 */
public class PropertiesTest {
    public static void main(String[] args) {
//        store();
        stringFormat();
    }


    private static void stringFormat(){

        String stringTemplet = "尊敬的${customer1}、${customer2}、${customer3}客户先生/女士," +
                "今天是${date},祝您们有个好的心情！";

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());


        Properties properties = new Properties();
        // 添加数据
        properties.put("customer1", "zhaoliying");
        properties.put("customer2", "gulinazha");
        properties.put("customer3", "gutianle");
        properties.put("date", DateUtils.getDateYear());

        // mybaits下的包
        String parse = PropertyParser.parse(stringTemplet, properties);
        System.out.println(parse);
    }

    
    /* *
     * @Author 吴槐
     * @Description 通过 Properties与 OutputStream 持久化数据
     * @Date 9:39 2022/7/28
     * @Param 
     * @return void
     *       
     **/
    private static void store() {
        Properties properties = new Properties();
        // 添加数据
        properties.setProperty("zhaoliying", "168");
        properties.setProperty("gulinazha", "172");
        properties.setProperty("gutianle", "179");
        // 将key取出，放入set集合中
        Set<String> ketSet = properties.stringPropertyNames();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File("E:/2.TXT"));
            // 持久化到文件中
            properties.store(outputStream, "SS");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outputStream)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 遍历Key
        for (String s : ketSet) {
            System.out.println(s); // key
            System.out.println(properties.getProperty(s));// value
        }

    }
}
