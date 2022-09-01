package com.tansun.collection.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {



    public static void main(String[] args) {
//        everyMap();

        Map map = new HashMap();  //定义Map集合对象
        map.put("apple","新鲜的苹果");  //向集合中添加对象
        map.put("computer","配置优良的计算机");
        map.put("book","堆积成山的图书");
        Collection<List> values = map.values();  //获取Map集合的value集合
        for(Object object:values){
            System.out.println("键值:"+object.toString());  //输出键值对象
        }
    }

    private static void everyMap() {
        Map<Object, Object> hashMap = new HashMap<>(17);
        System.out.println("当前map大小：" + hashMap.size());
        // linkedHashMap是 hashMap的子类，有序
        Map<Object, Object> linkedHashMap = new LinkedHashMap<>();

        // concurrentHashMap与 hashMap 同一级，线程安全的
        Map<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();

        // treeMap与 hashMap 同一级
        Map<Object, Object> treeMap = new TreeMap<>();

        // HashSet是 Set接口的子类，无序，不重复
        Set<Object> hashSet = new HashSet<>();

        // linkedHashSets是 hashSet的子类
        Set<Object> linkedHashSet = new LinkedHashSet<>();
    }
}