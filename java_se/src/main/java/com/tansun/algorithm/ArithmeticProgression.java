package com.tansun.algorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 求最短的等差数列有几项
 *    1、给定的序列是不是有序的？我们需不需要排序？
 *    2、为了保证等差数列尽可能短，我们应该如何去确定公差？
 *
 *    首先对于第一个问题，不言而喻我们肯定是需要先对接收到的数组进行排序。
 *    因为等差数列本身就是一个递增序列。其次思考第二个问题，如何保证数列尽可能短？
 *    思考这个问题前我们可以先思考如何让数组尽可能的长？理所当然，如果公差为1，
 *    长度就是数列中的最大值减去最小值了。反过来，为了数列尽可能短，我们得让公差尽可能大！
 */
public class ArithmeticProgression {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("输入一个已确定的已知数组个数：");
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;++i) arr[i]=sc.nextInt();
        //一定要先对数组进行排序
        Arrays.sort(arr);
        //先赋值为第一个相邻差值
        int cut=arr[1]-arr[0];
        for(int i=2;i<n;++i){
            //不断通过gcd公式获得最大公约数
            cut=gcd(cut,arr[i]-arr[i-1]);
        }
        //这里一定要特判公差为0的情况，否则下面会出现除零异常
        if(cut==0){
            System.out.println(n);
        }else
            //注意这里要加+1。因为前面获取的值不包括数列中最小的值
            System.out.println((arr[n-1]-arr[0])/cut+1);

    }
    //获取a和b的最大公约数，不了解的可以看前面的蓝桥真题
    static int gcd(int a,int b){
        return b == 0 ? a : gcd(b,a%b);
    }

}
