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

    public static void main(String[] args) {
        List<String> list = add();
        List<String> list2 = removeList(list);
        System.out.println(Arrays.toString(list2.toArray()));

    }

    private static List<String> add() {
        list = new ArrayList<>();// 默认大小是10，超过10时，才会进行数组扩容
        logger.info("创建ArrayList时大小{}", list.size());
        list.add("AAA");
        logger.info("第一次向ArrayList添加数据时大小{}", list.size());
        list.add("BBB");
        list.add("BBB");
        list.add("CCC");
        list.add("DDD");
        list.add("DDD");
        logger.info("ArrayList数组=={}", Arrays.toString(list.toArray()));
        return list;
    }

    private static List removeList(List<String> list) {
        // 正向遍历
        for (int i = 0; i < list.size(); i++) {
            if (("BBB").equals(list.get(i))) {
                list.add("EEE");
//                list.remove("BBB");
            }
            logger.info("第{}次遍历", i);
        }
        return list;
    }
}
