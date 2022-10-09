package com.tansun.annotation.validateannotation;
/**
 * @ClassName EntityParamsTest
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/13 8:53
 * @Copyright © 2020 阿里巴巴
 */
public class EntityParamsTest {
    public static void main(String[] args) {

        SysUserDept userDept = new SysUserDept();

        try {
            BaseEntity validate = userDept.validate();
            userDept = (SysUserDept)validate;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(userDept.toString());

    }
}
