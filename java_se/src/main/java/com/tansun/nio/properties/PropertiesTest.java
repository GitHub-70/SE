package com.tansun.nio.properties;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName PropertiesTest
 * @date 2020/11/11 14:45
 * @Copyright © 2020 阿里巴巴
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        store();
    }

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
