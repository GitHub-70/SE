package com.tansun.java8characteristics;

import com.tansun.utlis.DistinctUtil;

import java.math.BigDecimal;
import java.util.*;
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
        list.add(new Person("zhangsan", 12, new BigDecimal(123)));
        list.add(new Person("awang", 15, new BigDecimal(123)));
        list.add(new Person("lisi", 13, new BigDecimal(123)));
        list.add(new Person("wangwu", 14, new BigDecimal(123)));
        list.add(new Person("awang", 16, new BigDecimal(123)));
        list.add(new Person("awang", 16, new BigDecimal(123)));

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

        // 对name与age相同的记录进行去重
        List<Person> collect4 = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(()-> new TreeSet<>(
                                Comparator.comparing(p -> p.getName() + p.getAge()))),ArrayList :: new));
        System.out.println(collect4);

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

        // 对age字段进行累加
        Integer reduce1 = list.stream().map(Person::getAge).reduce((a, b) -> (a + b)).get();
        System.out.println(reduce1);

        // 对totAmt字段进行累加
        BigDecimal reduce2 = list.stream().map(Person::getTotAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(reduce2);

        // 多字段过滤
        List<Person> person = list.stream().filter(p->p.getName().equals("zhangsan") ||
                p.getName().equals("awang")).collect(Collectors.toList());
        System.out.println("person:"+person.toString());

        // 过滤结果为空
        List<Person> person1 = list.stream().filter(p -> p.getName().equals("aaa")).collect(Collectors.toList());
        // 这一行并没有输出
        person1.forEach(p -> System.out.println("p:"+p.toString()));
        System.out.println("person1:"+person1.toString());

        // 获取过滤的第一个结果
        Optional<Person> zhangsan = list.stream().filter(p -> {
            if (p.getName().equals("zhangsan2")) {
                return true;
            } else {
                return false;
            }
        }).findFirst();
        if (zhangsan.isPresent()){
            System.out.println("zhangsan2:"+zhangsan.get().toString());
        }
//        System.out.println(person2.toString());

//        String ages = list.stream().map((Person::getAge)).collect(Collectors.);

    }
}
