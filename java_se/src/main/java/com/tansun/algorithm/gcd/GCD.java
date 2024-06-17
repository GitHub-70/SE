package com.tansun.algorithm.gcd;

import java.util.Scanner;

/**
 * 最大公约数
 *      用gcd(m,n) 表示整数m和n的最大公约数：
 * 	如果m%n==0，那么gcd(m,n) 为n。
 * 	否则，gcd(m,n) 就是gcd(n，m%n)。
 * 	证明：假设m%n=r，那么m=(m/n)*n+r，能整除m和n的任意数字都必须也能整除r。因此 gcd(m,n) 和gcd(n,r)是一样的
 */
public class GCD {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first integer: ");
        int m = input.nextInt();
        System.out.println("Enter second integer: ");
        int n = input.nextInt();

        System.out.println("The greatest common divisor for "+ m +" and "+n+" is "+gcd(m,n));
        System.out.println("The greatest common divisor for "+ m +" and "+n+" is "+gcd2(m,n));

    }

    /**
     * 最大公约数
     * 时间复杂度O(n)
     * @param m
     * @param n
     * @return
     */
    public static int gcd(int m, int n){
        int gcd = 1;
        if (m >= n && m % n == 0)
            return n;

        if (m < n && n % m == 0)
            return m;
        // 两者取小值
        int min = Math.min(m, n);
        // 最大公约数，从小值的一半开始取
        for (int k = (min/2); k >= 1; k--){
            if (m%k == 0 && n%k == 0){
                gcd = k;
                break;
            }
        }
        return gcd;
    }


    /**
     * 最大公约数
     * 欧几里得算法
     * @param m
     * @param n
     * @return
     *
     * 最后，也是最基本的最重要的
     * 当题目的数据范围达到了10^18的时候，很显然就要用O(logn)的算法或数据结构了
     */
    public static int gcd2(int m,int n){
        if (m%n == 0)
            return n;
        else
            // 最坏的情况，时间复杂度是Olog(n)
            return gcd2(n,m%n);
    }

    /**
     * 欧几里得优化版
     * @param m
     * @param n
     * @return
     */
    public static int gcd3(int m,int n){
        if (m>=n){
            if (m%n == 0)
                return n;
            else
                // 最坏的情况，时间复杂度是Olog(n)
                return gcd3(n,m%n);
        } else {
            if (n%m == 0)
                return m;
            else
                // 最坏的情况，时间复杂度是Olog(n)
                return gcd3(m,n%m);
        }

    }
}


