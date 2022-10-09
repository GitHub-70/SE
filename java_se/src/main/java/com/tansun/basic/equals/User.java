package com.tansun.basic.equals;

import java.util.Objects;

/**
 * @ClassName user
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/9/22 10:50
 * @Copyright © 2020 阿里巴巴
 */
public class User {

    private Integer id;
    private String name;
    private String pwd;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    /* *
     * @Author 吴槐
     * @Description 为什么重写equals方法，还必须要重写hashcode方法
     *  https://blog.csdn.net/wdy00000/article/details/124439846
     * @Date 9:45 2022/9/22
     * @Param obj
     * @return boolean
     *
     * 重写后两个方法的关系：
     *  equals()相等的两个对象，hashcode()一定相等；
     *  hashcode()不等，一定能推出equals()也不等；
     *  hashcode()相等，equals()可能相等，也可能不等。
     *  所以先进行hashcode（）判断，不等就不用equals（）方法了。
     *  但equels是是根据对象的特征进行重写的，有时候特征相同，但hash值不同，
     *  也不是一个对象。 所以两个都重写才能保障是同一个对象。
     *
     **/

    @Override
    public boolean equals(Object obj) {
        // 如果是同一个对象（地址值相等），返回true
        if (this == obj){
            return true;
        }
        // 如果对象为空，或者不是一个对象类型 返回false
        if (null == obj || this.getClass() != obj.getClass()){
            return false;
        }

        // 判断属性是否相等，
        if (obj instanceof User){
            User user = (User)obj;
            return id== user.id && name.equals(user.name) && pwd.equals(user.pwd);
        }

        return false;
    }

    // 方式一
//    @Override
//    public int hashCode() {
//        // 利用 Objects 的 hash 计算所有属性的 hashcode
//        return Objects.hash(id, name, pwd);
//    }

    // 方式二
    // 自定义重写 hashcode
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (pwd == null ? 0 : pwd.hashCode());
        return result;
    }
}
