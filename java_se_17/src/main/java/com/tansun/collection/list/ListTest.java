package com.tansun.collection.list;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName ListTest
 * @date 2020/11/10 15:26
 * @Copyright © 2020 阿里巴巴
 */
public class ListTest {
    private static Logger logger = LoggerFactory.getLogger(ListTest.class);
    private static List<String> list;
    private static Set<String> set;

    public static void main(String[] args) {

        listNoChangeSize();

    }

    /**
     * 集合不可变
     * java 9 引入了List.of()方法，该方法返回一个不可变的List,不可添加或删除元素
     */
    private static void listNoChangeSize() {
        List<String> miJi = List.of("九阴真经");
        List<String> strings = List.of("他强任他强", "清风拂山岗", "他横任他横", "明月照大江");
        System.out.println(strings.size());
//        strings.add("乾坤大挪移");
    }


}
