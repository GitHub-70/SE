package com.tansun.collection.list;

import com.google.common.collect.Lists;
import com.tansun.basic.pojo.SysRole;
import com.tansun.basic.pojo.SysUser;
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
//        listAddRemove();

//        iteratorOrder();

        listObjectTransfer();
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
//                list.remove("BBB");// 正向遍历--数据漏删
            }
            logger.info("第{}次遍历", i);
        }
        return list;
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
}
