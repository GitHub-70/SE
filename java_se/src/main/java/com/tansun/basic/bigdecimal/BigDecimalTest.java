package com.tansun.basic.bigdecimal;

import java.math.BigDecimal;

/**
 * @ClassName BigDecimalTest
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/6/15 14:33
 * @Copyright © 2020 阿里巴巴
 */
public class BigDecimalTest {
    /* *
        1：
        scale指的是你小数点后的位数。比如123.456则score就是3.
        score()就是BigDecimal类中的方法啊。
        比如:BigDecimal b = new BigDecimal("123.456");
        b.scale(),返回的就是3.
        2：
        roundingMode是小数的保留模式。它们都是BigDecimal中的常量字段,有很多种。
        比如：BigDecimal.ROUND_HALF_UP表示的就是4舍5入。
        3：
        pubilc BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
        的意思是说：我用一个BigDecimal对象除以divisor后的结果，并且要求这个结果保留有scale个小数位，roundingMode表示的就是保留模式是什么，是四舍五入啊还是其它的，你可以自己选！
     **/
    public static void main(String[] args) {

//        bigDecimal_ROUND_DOWN();

//        bigDecimalToStringFromat();

        bigDecimalOfAbs();
    }

    private static void bigDecimal_round_up(){
        BigDecimal bigDecimal = new BigDecimal("1.221").setScale(2, BigDecimal.ROUND_UP);
        System.out.println(bigDecimal); // 1.23 不管舍弃位是几 直接进一位

    }

    private static void bigDecimal_ROUND_DOWN(){
        BigDecimal bigDecimal = new BigDecimal("1.2266").setScale(2, BigDecimal.ROUND_DOWN);
        System.out.println(bigDecimal); // 1.22 不管舍弃位是几 直接去掉多余的位数
        System.out.println("转成字符串:"+bigDecimal.toPlainString()); // 转成字符串

    }

    private static void bigDecimal_ROUND_CEILING(){
        // 值为正数
        BigDecimal bigDecimal = new BigDecimal("1.2246").setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(bigDecimal); // 1.23 不管舍弃位是几 都向上进一位

        // 值为负数
        BigDecimal bigDecimal1 = new BigDecimal("-1.2256").setScale(2,BigDecimal.ROUND_CEILING);
        System.out.println(bigDecimal1); // -1.22 不管舍弃位是几 直接删掉

    }


    private static void bigDecimal_ROUND_FLOOR(){
        // 值为正数的时候
        BigDecimal bigDecimal = new BigDecimal("1.2266").setScale(2, BigDecimal.ROUND_FLOOR);
        System.out.println(bigDecimal); // 1.22 不管舍弃位是几 直接去掉多余的位数

        // 值为负数的时候
        BigDecimal bigDecimal1  = new BigDecimal("-1.2226").setScale(2, BigDecimal.ROUND_FLOOR);
        System.out.println(bigDecimal1); // -1.23 不管舍弃位是几 负数时会进一位

    }


    private static void bigDecimal_ROUND_HALF_UP(){
        BigDecimal bigDecimal = new BigDecimal("1.225").setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal); // 1.23 四舍五入（若舍弃位的的第一个数 >=5 就进一位）

    }


    private static void bigDecimal_ROUND_HALF_DOWN(){
        BigDecimal bigDecimal = new BigDecimal("1.225").setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(bigDecimal); // 1.22 四舍五入（若舍弃位的的第一个数 >5 就进一位）

    }


    private static void bigDecimal_ROUND_HALF_EVEN(){
        /**
         * 这边特殊一点，是根据保留的最后一位小数的奇偶性来判断的
         * 这边保留的最后一位是2 偶数  只有当 >5 时 才能进一位
         */
        BigDecimal bigDecimal = new BigDecimal("1.225").setScale(2, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(bigDecimal); // 1.22


        /**
         * 这边保留的最后一位是1 奇数 当>=5时 才能进一位
         */
        BigDecimal bigDecimal1 = new BigDecimal("1.215").setScale(2, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(bigDecimal1); // 1.22

    }


    private static void bigDecimal_ROUND_UNNECESSARY(){
        BigDecimal bigDecimal = new BigDecimal("1.2266").setScale(2,BigDecimal.ROUND_UNNECESSARY);
        System.out.println(bigDecimal); // 会抛出ArithmeticException异常

    }


    /* *
     * @Author 吴槐
     * @Description 金额常见正确错误写法（数值型会丢失精度）
     * @Date 14:41 2022/6/15
     * @Param 
     * @return void
     *       
     **/
    private static void bigDecimalRightInput(){
        BigDecimal bigDecimal1 = new BigDecimal(1.2215667); // 这种写法会造成精度丢失的

        BigDecimal bigDecimal2 = new BigDecimal(10); // 这种写法是可以的

        BigDecimal bigDecimal3 = new BigDecimal("1.2215667"); // 这么写不会丢精度
        int count = bigDecimal3.scale(); // 7 返回小数点后保留了几位

        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);
        System.out.println(bigDecimal3);
    }

    /* *
     * @Author 吴槐
     * @Description bigDecimal转String 的表现形式
     * @Date 11:16 2022/6/28
     * @Param
     * @return void
     *
     **/
    private static void bigDecimalToStringFromat(){
        // 11后面21个0
        BigDecimal bigDecimal = new BigDecimal("11E21");

        System.out.println(bigDecimal);
        // 不使用任何指数，用法 将科学计数法表示的数转为一个不含指数的数
        System.out.println("使用数值表示一个数:"+bigDecimal.toPlainString());
        // 用科学计数法表示一个数
        System.out.println("用科学计数法表示一个数:"+bigDecimal.toString());
        // 工程记数法表示一个数 工程记数法是一种工程计算中经常使用的记录数字的方法，
        // 与科学技术法类似，但要求10的幂必须是3的倍数
        System.out.println("工程记数法表示一个数"+bigDecimal.toEngineeringString());
    }

    private static void bigDecimalOfAbs(){
        BigDecimal bigDecimal = new BigDecimal("1.2215667"); // 这么写不会丢精度
        BigDecimal bigDecima2 = new BigDecimal("-1.2215667"); // 这么写不会丢精度

        System.out.println(bigDecimal);
        System.out.println(bigDecima2.abs());// 取绝对值
        System.out.println(bigDecima2.negate()); // 取反
    }
}
