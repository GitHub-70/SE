package com.tansun.java8characteristics;

import com.tansun.utlis.DistinctUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName StreamUse
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/8/16 8:17
 * @Copyright © 2020 阿里巴巴
 */
public class StreamUse {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("zhangsan", 12));
        list.add(new Person("awang", 15));
        list.add(new Person("lisi", 13));
        list.add(new Person("wangwu", 14));
        list.add(new Person("awang", 16));

        // 取出前两条数据
        Stream<Person> personStream = list.stream().limit(2);
        List<Person> collect = personStream.collect(Collectors.toList());
        System.out.println(collect);

        // 调过前两条，取出第一条
        Stream<Person> skip = list.stream().skip(2).limit(1);
        List<Person> collect1 = skip.collect(Collectors.toList());
        System.out.println(collect1);

        // 取出前三条数据
        List<Person> people = list.subList(0, 3);
        System.out.println(people);

        // 对list中的name字段进行拼接
        String names = list.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println(names);

        // 对list中的name字段去重后进行拼接
        String names2 = list.stream().map(Person::getName).distinct().collect(Collectors.joining());
        System.out.println(names2);

        // 去掉name相同的重复记录
        List<Person> collect2 = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(()-> new TreeSet<>(
                                Comparator.comparing(Person::getName))),ArrayList :: new));
        System.out.println(collect2);

        // 去掉name相同的重复记录
        List<Person> collect3 = list.stream()
                .filter(DistinctUtil.distinctByKey(Person::getName))
                .collect(Collectors.toList());
        System.out.println(collect3);

//        String ages = list.stream().map((Person::getAge)).collect(Collectors.);

    }
}
