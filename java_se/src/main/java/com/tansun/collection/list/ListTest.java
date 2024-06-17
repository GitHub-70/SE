package com.tansun.collection.list;

import com.google.common.collect.Lists;
import com.tansun.basic.pojo.SysRole;
import com.tansun.basic.pojo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        listAddRemove();

//        iteratorOrder();

//        listObjectTransfer();

//        iteratorRemove();

//        removeAll();

//        listAddUniqueValue();

    }

    /**
     * 添加单例的唯一的对象
     * Collections.singletonList被限定只被分配一个内存空间，也就是只能存放一个元素的内容。
     * 这样做的好处就是不会造成内存的浪费，不像ArrayList这样的类，不管你是需要多少内存，
     * 初始化就会被分配一定空间的内存，就会导致多余内存的浪费。
     *
     * 这个List中只能存放一个元素，多一个或者少一个都会导致异常。
     */
    private static void listAddUniqueValue() {
        String str = "分配一个单例的唯一对象";
        list = Collections.singletonList(str);
        System.out.println(list);
//        list.add("dfd");
//        System.out.println(list);
        list.remove(str);
        System.out.println(list);
    }

    private static void removeAll() {
        List<String> list = add();
        List<String> list2 = new ArrayList<>();
        list2.add("BBB");
        list2.add("CCC");
        list.removeAll(list2);
        System.out.println(list);
    }

    /**
     * list对象转换
     */
    private static void listObjectTransfer() {
        List<SysUser> sysUsers = new ArrayList<>();
        List<SysRole> sysRoles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SysRole sysRole = new SysRole();
            sysRole.setId(i+1);
            sysRole.setName("小白"+i);
            sysRole.setNote("备注"+i);
            sysRole.setCreatedUser("admin");
            sysRoles.add(sysRole);
        }
        sysUsers = Lists.transform(sysRoles, sysRole -> {
            SysUser sysUser = new SysUser();
            sysUser.setUsername(sysRole.getName());
            sysUser.setCreatedUser(sysRole.getCreatedUser());
            return sysUser;
        });
        System.out.println(sysUsers.toString());
    }


    private static void listAddRemove() {
        List<String> list = add();
        Set<String> addSet = addSet();
//        List<String> list2 = removeList(list);
//        List<String> list2 = removeList2(list);
//        List<String> list2 = removeList3(list);
        Set<String> set1 = removeList4(addSet);
        System.out.println(Arrays.toString(set1.toArray()));
    }

    private static List<String> add() {
        list = new ArrayList<>();// 默认大小是10，超过10时，才会进行数组扩容
        logger.info("创建ArrayList时大小{}", list.size());
        list.add("AAA");
        list.add("AAA");
        list.add("AAA");
        list.add("AAA");
        list.add("AAA");
        logger.info("第一次向ArrayList添加数据时大小{}", list.size());
        list.add("BBB");
        list.add("BBB");
        list.add("CCC");
        list.add("DDD");
        list.add("DDD");
        logger.info("ArrayList数组=={}", Arrays.toString(list.toArray()));
        logger.info("ArrayList数组=={}", list);
        return list;
    }

    private static Set<String> addSet() {
        set = new HashSet<>();// 默认大小是10，超过10时，才会进行数组扩容
        logger.info("创建HashSet时大小{}", set.size());
        set.add("AAA");
        set.add("AAA");
        set.add("AAA");
        set.add("AAA");
        set.add("AAA");
        logger.info("第一次向HashSet添加数据时大小{}", set.size());
        set.add("BBB");
        set.add("BBB");
        set.add("CCC");
        set.add("DDD");
        set.add("DDD");
        logger.info("HashSet数组=={}", Arrays.toString(set.toArray()));
        return set;
    }

    /**
     * fori遍历
     * @param list
     * @return
     */
    private static List removeList(List<String> list) {
        // 正向遍历
        for (int i = 0; i < list.size(); i++) {
            if (("BBB").equals(list.get(i))) {
//                list.remove("BBB");// 正向遍历--数据漏删
                list.remove(i);// 正向遍历--数据漏删
            }
            logger.info("第{}次遍历", i);
        }
        return list;
    }

    /**
     * 逆向遍历删数据
     * @param list
     * @return
     */
    private static List removeList2(List<String> list) {
        // 逆向遍历
        for (int i = list.size()-1; i >= 0; i--) {
            if (("AAA").equals(list.get(i))) {
                list.remove(i);
            }
            logger.info("第{}次遍历", i);
        }
        return list;
    }

    /**
     * foreach遍历
     * @param list
     * @return
     */
    private static List removeList3(List<String> list) {
        // 正向遍历
        int i = 0;
        for (String s : list) {
            if (("AAA").equals(s)) {
                list.remove("AAA");//
            }
            i++;
            logger.info("第{}次遍历", i);
        }
        return list;
    }

    /**
     * foreach遍历
     * @param set
     * @return
     */
    private static Set removeList4(Set<String> set) {
        // 正向遍历
        int i = 0;
        for (String s : set) {
            if (("AAA").equals(s)) {
                set.remove("AAA");//
            }
            i++;
            logger.info("第{}次遍历", i);
        }
        return set;
    }


    /* *
     * @Author 吴槐
     * @Description  验证增强for循环的迭代顺序
     * @Date 15:16 2022/6/17
     * @Param 
     * @return java.util.List
     *       
     **/
    private static void iteratorOrder(){
        List<String> list = new ArrayList<>();
        list.add("A1");
        list.add("B");
        list.add("C");
        list.add("A3");
        list.add("D");
        list.add("E");
        for (String val: list ){
            System.out.println(val);
        }
    }

        private static void iteratorRemove(){
            List<Integer> Ids = new ArrayList<>();
            Ids.add(1);
            Ids.add(2);
            Ids.add(3);
            List<SysRole> sysRoles = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                SysRole sysRole = new SysRole();
                sysRole.setId(i+1);
                sysRole.setName("小白"+i);
                sysRole.setNote("备注"+i);
                sysRole.setCreatedUser("admin");
                sysRoles.add(sysRole);
                if (i == 1 || i == 3){
                    SysRole sysRole2 = new SysRole();
                    sysRole2.setId(i);
                    sysRoles.add(sysRole2);
                }
            }
            System.out.println("sysRoles:"+sysRoles);
            List<Integer> idList = sysRoles.stream().map(SysRole::getId).collect(Collectors.toList());
            for (Integer id : Ids) {
                if (idList.contains(id)){
                    sysRoles = sysRoles.stream().filter(p -> !id.equals(p.getId())).collect(Collectors.toList());
                }
            }
            System.out.println("sysRoles="+sysRoles);
    }
}
