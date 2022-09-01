package com.tansun.java8characteristics;

import sun.awt.SunHints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {

    public static void main(String[] args) {

        Map<String, Integer> personMap = get_NormalValue_ListToMap();
        Map<String, Integer> repeatKeyMap = get_RepeatKey_ListToMap();
        Map<String, Integer> repeatKeyMap2 = get_RepeatKey2_ListToMap();
        Map<String, Integer> repeatKeyMap3 = get_RepeatKey3_ListToMap();
        Map<String, Integer> personMap2 = get_NormalValue2_ListToMap();

        System.out.println(personMap);
        System.out.println(repeatKeyMap);
        System.out.println(repeatKeyMap2);
        System.out.println(repeatKeyMap3);
        System.out.println(personMap2);
    }

    private static Map<String, Integer> get_NormalValue_ListToMap() {
        List personList = new ArrayList<>();
        personList.add(new Person("zhangsan", 23));
        personList.add(new Person("lisi", 22));
        personList.add(new Person("wangwu", 25));
        personList.add(new Person("lixiaoyao", 24));

        // 将 list 转换为 map
        return (Map)personList.stream().collect(Collectors.toMap(Person :: getName, Person :: getAge));
    }

    /**
     *  重复key
     *
     * @return
     */
    private static Map<String, Integer> get_NoNormalValue_ListToMap() {
        List personList = new ArrayList<>();
        personList.add(new Person("zhangsan", 23));
        personList.add(new Person("lisi", 22));
        personList.add(new Person("wangwu", 25));
        personList.add(new Person("zhangsan", 24));

        // 将 list 转换为 map
        return (Map)personList.stream().collect(Collectors.toMap(Person :: getName, Person :: getAge));
    }

    /**
     *  重复key
     *      重复时用后面的value 覆盖前面的value
     * @return
     */
    private static Map<String, Integer> get_RepeatKey_ListToMap() {
        List personList = new ArrayList<>();
        personList.add(new Person("zhangsan", 23));
        personList.add(new Person("lisi", 22));
        personList.add(new Person("wangwu", 25));
        personList.add(new Person("zhangsan", 24));

        // 将 list 转换为 map
        return (Map)personList.stream().collect(Collectors.toMap(Person :: getName, Person :: getAge, (key1, key2) -> key2));
    }

    /**
     *  重复key
     *      重复时将前面的value 和后面的value拼接起来
     * @return
     */
    private static Map<String, Integer> get_RepeatKey2_ListToMap() {
        List personList = new ArrayList<>();
        personList.add(new Person("zhangsan", "Aa"));
        personList.add(new Person("lisi", "Bb"));
        personList.add(new Person("wangwu", "Cc"));
        personList.add(new Person("zhangsan", "Dd"));

        // 将 list 转换为 map
        return (Map)personList.stream().collect(Collectors.toMap(Person :: getName, Person :: getAddr, (key1, key2) -> key1 + "," + key2));
    }

    /**
     *  重复key
     *      重复时将重复key的数据组成集合
     * @return
     */
    private static Map<String, Integer> get_RepeatKey3_ListToMap() {
        List personList = new ArrayList<>();
        personList.add(new Person("zhangsan", "A"));
        personList.add(new Person("lisi", "B"));
        personList.add(new Person("wangwu", "C"));
        personList.add(new Person("zhangsan", "D"));

        // 将 list 转换为 map
        return (Map)personList.stream().collect(Collectors.toMap(Person :: getName,
                p -> {
                    List listAddr = new ArrayList();
                    listAddr.add(p.addr);
                    return listAddr;
                },
                (List<String> value1, List<String> value2) -> {
                    value1.addAll(value2);
                    return value1;
                }
        ));
    }

    /**
     *  value 为 null
     *
     * @return
     */
    private static Map<String, Integer> get_NoNormalValue2_ListToMap() {
        List personList = new ArrayList<>();
        Person lixiaoyao = new Person("lixiaoyao", "");
        lixiaoyao.setAddr(null);
        personList.add(new Person("zhangsan", "AA"));
        personList.add(new Person("lisi", "BB"));
        personList.add(new Person("wangwu", "CC"));
        personList.add(lixiaoyao);

        // 将 list 转换为 map
        return (Map)personList.stream().collect(Collectors.toMap(Person :: getName, Person :: getAge));
    }

    /**
     *  value 为 null
     *
     * @return
     */
    private static Map<String, Integer> get_NormalValue2_ListToMap() {
        List personList = new ArrayList<>();
        Person lixiaoyao = new Person("lixiaoyao", "");
        lixiaoyao.setAddr(null);
        personList.add(new Person("zhangsan", "AAA"));
        personList.add(new Person("lisi", "BBB"));
        personList.add(new Person("wangwu", "CCC"));
        personList.add(lixiaoyao);

        // 将 list 转换为 map
        return (Map)personList.stream().collect(Collectors.toMap(Person :: getName,
                p -> {
                    List listAddr = new ArrayList();
                    listAddr.add(p.addr);
                    return listAddr;
                },
                (List<String> value1, List<String> value2) -> {
                    value1.addAll(value2);
                    return value1;
                }
        ));
    }


//    private static List<Person> get_List_WhereAge() {
//        List personList = new ArrayList<>();
//        personList.add(new Person("zhangsan", 23));
//        personList.add(new Person("lisi", 22));
//        personList.add(new Person("wangwu", 25));
//        personList.add(new Person("lixiaoyao", 24));
//
//        // 将 list 转换为 map
//        return personList.stream().forEach();
//    }


}

class Person{
    String name;
    Integer age;
    String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                '}';
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }
}
