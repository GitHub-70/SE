package com.tansun.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://www.cnblogs.com/best/p/5748105.html
 *
 * Spring表达式语言（简称SpEL）是一个支持查询并在运行时
 * 操纵一个对象图的功能强大的表达式语言。SpEL语言的语法
 * 类似于统一EL，但提供了更多的功能，最主要的是显式
 * 方法调用和基本字符串模板函数。
 *
 *
 */

public class EvaluationContextTest {

    public static void main(String[] args) throws NoSuchMethodException {
        //创建SpEL表达式的解析器
        ExpressionParser parser=new SpelExpressionParser();

        User user = new User(1008,"周星驰",25);
        User user2 = new User(9002,"吴孟达",35);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        //解析表达式需要的上下文，解析时有一个默认的上下文
        EvaluationContext ctx = new StandardEvaluationContext();
        //在上下文中设置变量，变量名为user，内容为user对象
        ctx.setVariable("user", user);
        ctx.setVariable("users", users);
        //从用户对象中获得id并+1900，获得解析后的值在ctx上下文中
        int userId1 = (Integer) parser.parseExpression("#user.getUserId() - 1000").getValue(ctx);//通过对象的方法
        int userId2 = (Integer) parser.parseExpression("#user.userId + 1900").getValue(ctx);//通过对象的属性
        // 集合中的索引获取
        String userName = parser.parseExpression("#users[1].userName").getValue(ctx, String.class);
        System.out.println(userId1);
        System.out.println(userId2);
        System.out.println(userName);

        // 列表 {}本身意味着一个空列表
        List listOfLists = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue();
        System.out.println(((List)listOfLists.get(1)).get(1));

        // 数组
        String[] students=new String[]{"tom","jack","rose","mark","lucy"};
        ctx.setVariable("students", students);
        String student = parser.parseExpression("#students[3]").getValue(ctx, String.class);
        System.out.println(student);

        // map {:}本身就意味着一个空Map
        Map inventorInfo = (Map) parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue();
        Map mapOfMaps = (Map) parser.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}").getValue();
        System.out.println(inventorInfo.get("dob"));
        System.out.println(((Map)mapOfMaps.get("name")).get("first"));


        // 没有#号，方法调用
        String c = parser.parseExpression("'abcdef'.substring(2, 3)").getValue(String.class);
        System.out.println("截取：" + c);

        // 操作符 < > ==
        boolean trueValue1 = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);
        boolean trueValue2 = parser.parseExpression("2 between {1,9} ").getValue(Boolean.class);
        //false，字符xyz是否为int类型
        boolean falseValue2 = parser.parseExpression("'xyz' instanceof T(int)").getValue(Boolean.class);
        System.out.println(trueValue1);
        System.out.println(trueValue2);
        System.out.println(falseValue2);

        //逻辑运算 and or
        boolean falseValue4 = parser.parseExpression("true and false").getValue(Boolean.class);
        //true，isMember方法用于测试是否为某个对象的成员
//        String expression = "isMember(false)";
//        boolean trueValue4 = parser.parseExpression(expression).getValue(Boolean.class);
//        System.out.println(falseValue4);
//        System.out.println(trueValue4);

        // 三元运算
        User user3 = parser.parseExpression("#user != null ? #user : null").getValue(ctx, User.class);
        // 三目运算
        User user4 = parser.parseExpression("#user ?: null").getValue(ctx, User.class);
        System.out.println(user3);
        System.out.println(user4);
        int userID = parser.parseExpression("#user.userId != null ? #user.userId = 2343 : #user.userId").getValue(ctx, Integer.class);
        System.out.println(userID);

        // 特殊的 T 运算符指定 java.lang.Class 的实例（类型）。
        Class value = parser.parseExpression("T(String)").getValue(Class.class);
        // 类中的静态变量、静态方法属于Class， 可以通过T(xxx).xxx调用。
        String Id = parser.parseExpression("T(Integer).toString(008)").getValue(String.class);
        System.out.println(Id);

        // 函数可以当做一种变量来注册和使用的
        // 注册 org.springframework.util.StringUtils.startsWithIgnoreCase(String str,String prefix)
        Method method = StringUtils.class.getDeclaredMethod("startsWithIgnoreCase", String.class, String.class);
        ctx.setVariable("startsWithIgnoreCase", method);
        Boolean valueTrue = parser.parseExpression("#startsWithIgnoreCase('AFD','a')").getValue(ctx, Boolean.class);
        System.out.println(valueTrue);
    }

}

class User{

    Integer userId;
    String userName;
    Integer age;

    public User(Integer userId, String userName, Integer age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}