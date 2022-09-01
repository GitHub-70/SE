package com.tansun.algorithm;

import java.util.Scanner;

/**
 * 连号区间数
 *
 * 如果这段序列最大值为max，最小值为min。如果max-min==j-i，
 * 那么说明这段子数组就是一段公差为1的等差数列。
 *
 */
public class EvenTheNumberRange {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("输入一个已确定的已知数组个数：");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;++i) arr[i]=sc.nextInt();
        int ans = 0;
        for(int i=0;i<n;++i) {
            int max=arr[i];
            int min=arr[i];
            // 当前数据与下一个数据进行对比，
            for(int j=i;j<n;++j) {
                max=Math.max(max, arr[j]);
                min=Math.min(min,arr[j]);
                // 数值差与数组下标差相等，则是连续区间
                if(max-min == j-i) ans++;
            }
        }
        System.out.println(ans);
    }


}
