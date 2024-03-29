package com.tansun.randomnumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName RandomOrMath
 * @date 2020/11/1 23:03
 * @Copyright © 2020 阿里巴巴
 */
public class RandomOrMath {
    private static Logger Logger = LoggerFactory.getLogger(RandomOrMath.class);
    private static Random random = new Random();
    public static void main(String[] args) {
//        random();
        math();
    }
    private static void random(){
        System.out.println(random.nextInt(5));
        System.out.println(random.nextDouble());
        System.out.println(random.nextBoolean());// 随机产生true或false
    }

    private static void math(){
        // Math.sqrt()//计算平方根 Math.cbrt()//计算立方根 Math.hypot(x,y)//计算 (x的平方+y的平方)的平方根
        Logger.info("TAG","Math.sqrt(16)----:"+Math.sqrt(16));//4.0
        Logger.info("TAG","Math.cbrt(8)----:"+Math.cbrt(8));//2.0
        Logger.info("TAG","Math.hypot(3,4)----:"+Math.hypot(3,4));//5.0

        // Math.pow(a,b)//计算a的b次方 Math.exp(x)//计算e^x的值
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.pow(3,2)----:"+Math.pow(3,2));//9.0
        Logger.info("TAG","Math.exp(3)----:"+Math.exp(3));//20.085536923187668

        //Math.max();//计算最大值 Math.min();//计算最小值
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.max(2.3,4.5)----:"+Math.max(7,15));//15
        Logger.info("TAG","Math.min(2.3,4.5)----:"+Math.min(2.3,4.5));//2.3

        //Math.abs求绝对值
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.abs(-10.4)----:"+Math.abs(-10.4));//10.4
        Logger.info("TAG","Math.abs(10.1)----:"+Math.abs(10.1));//10.1

        //Math.ceil天花板的意思，就是返回大的值
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.ceil(-10.1)----:"+Math.ceil(-10.1));//-10.0
        Logger.info("TAG","Math.ceil(10.7)----:"+Math.ceil(10.7));//11.0
        Logger.info("TAG","Math.ceil(-0.7)----:"+Math.ceil(-0.7));//-0.0
        Logger.info("TAG","Math.ceil(0.0)----:"+Math.ceil(0.0));//0.0
        Logger.info("TAG","Math.ceil(-0.0)----:"+Math.ceil(-0.0));//-0.0
        Logger.info("TAG","Math.ceil(-1.7)----:"+Math.ceil(-1.7));//-1.0

        //Math.floor地板的意思，就是返回小的值
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.floor(-10.1)----:"+Math.floor(-10.1));//-11.0
        Logger.info("TAG","Math.floor(10.7)----:"+Math.floor(10.7));//10.0
        Logger.info("TAG","Math.floor(-0.7)----:"+Math.floor(-0.7));//-1.0
        Logger.info("TAG","Math.floor(0.0)----:"+Math.floor(0.0));//0.0
        Logger.info("TAG","Math.floor(-0.0)----:"+Math.floor(-0.0));//-0.0

        //Math.random 取得一个大于或者等于0.0小于不等于1.0的随机数[0,1)
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.random()----:"+Math.random());//输出[0,1)间的随机数 0.8979626325354049
        Logger.info("TAG","Math.random()*100----:"+Math.random()*100);//输出[0,100)间的随机数 32.783762836248144

        // Math.rint 四舍五入 返回double值
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.rint(10.1)----:"+Math.rint(10.1));//10.0
        Logger.info("TAG","Math.rint(10.7)----:"+Math.rint(10.7));//11.0
        Logger.info("TAG","Math.rint(-10.5)----:"+Math.rint(-10.5));//-10.0
        Logger.info("TAG","Math.rint(-10.51)----:"+Math.rint(-10.51));//-11.0
        Logger.info("TAG","Math.rint(-10.2)----:"+Math.rint(-10.2));//-10.0
        Logger.info("TAG","Math.rint(9)----:"+Math.rint(9));//9.0

        //Math.round 四舍五入 float时返回int值，double时返回long值

        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.round(10.1)----:"+Math.round(10.1));//10
        Logger.info("TAG","Math.round(10.7)----:"+Math.round(10.7));//11
        Logger.info("TAG","Math.round(-10.5)----:"+Math.round(-10.5));//-10
        Logger.info("TAG","Math.round(-10.51)----:"+Math.round(-10.51));//-11
        Logger.info("TAG","Math.round(-10.2)----:"+Math.round(-10.2));//-10
        Logger.info("TAG","Math.round(9)----:"+Math.round(9));//9

        //Math.nextUp(a) 返回比a大一点点的浮点数
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.nextUp(1.2)----:"+Math.nextUp(1.2));//1.2000000000000002

        //Math.nextDown(a) 返回比a小一点点的浮点数
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.nextDown(1.2)----:"+Math.nextDown(1.2));//1.1999999999999997
        
        //Math.nextAfter(a,b) 返回(a,b)或(b,a)间与a相邻的浮点数 b可以比a小
        Logger.info("TAG","------------------------------------------");
        Logger.info("TAG","Math.nextAfter(1.2, 2.7)----:"+Math.nextAfter(1.2, 2.7));//1.2000000000000002
        Logger.info("TAG","Math.nextAfter(1.2, -1)----:"+Math.nextAfter(1.2, -1));//1.1999999999999997


    }

}
