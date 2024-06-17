package com.tansun.numerical.law;

import java.math.BigDecimal;

/**
 * 统计数组中逆序对的个数
 *
 * 走马灯数
 * 7 迎来爆发，变得混沌
 */
public class NumberLoop {

    public static void main(String[] args) {
//        int[] nums = {1,4,2,8,5,7};
        int num1 = 142857;
        int n = 14;
        for (int i = 1; i <= n; i++) {
            int base = num1*i;
            System.out.print(base);
            String baseString = String.valueOf(base);
            char[] chars = baseString.toCharArray();
            int baseSum = 0;
            for (int j = 0; j < chars.length; j++) {
                baseSum += Integer.parseInt(String.valueOf(chars[j]));
            }
            System.out.print("---");
            System.out.print(baseSum);
            System.out.print("---");
            System.out.println(new BigDecimal(i).divide(new BigDecimal("7"),12,BigDecimal.ROUND_HALF_UP));
        }
        n = 7;// 7 14 21
        for (int i = 1; i <= n; i++) {
            System.out.print(i*7);
            System.out.print("---");
            System.out.println(new BigDecimal(num1).divide(new BigDecimal(i*7),12,BigDecimal.ROUND_HALF_UP));
        }
        double loop = 20408.14285714;
        BigDecimal divide = new BigDecimal(num1).divide(new BigDecimal("7"), 12, BigDecimal.ROUND_HALF_UP);
        System.out.println(Math.sqrt(divide.doubleValue()));


    }



    public static int numberLoop(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    count++;
                }
            }
            result += count;
        }
        return result;
    }
}
