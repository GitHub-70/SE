package com.tansun.algorithm;

import java.util.Arrays;
import java.util.Scanner;
/**
 * 递增三元组
 * 给定三个整数数组
 *  A = [A1, A2, ... AN],
 *  B = [B1, B2, ... BN],
 *  C = [C1, C2, ... CN]，
 *  请你统计有多少个三元组(i, j, k) 满足：
 *  1. 1 <= i, j, k <= N
 *  2. Ai < Bj < Ck
 *
 * 这道题的要求我们分别从A,B,C找出三个数。保证A[i]<B[j]<C[k]。但是对ijk三者的关系并没有要求。
 * 所以由此我们首选可以想到去排序。那我们应该去排序哪些数组呢？ABC全都排序吗？没有必要！
 * 我们只需要对AC数组进行排序，然后去遍历B数组即可。通过二分查找从A数组找到小于B[i]的最大值
 * 下标，从C数组找到大于B[i]的最小值下标。这样就能获得A组中小于B[i]的个数和C组中大于B[i]的
 * 个数。两者一相乘就是选择B[i]情况下能组成递增三元组的数目，以此遍历一遍B数组即可获得答案
 *
 */
public class IncreasingTriplet {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        for(int i = 0; i < n; ++i) {
            A[i] = sc.nextInt();
        }
        for(int i = 0; i < n; ++i) {
            B[i] = sc.nextInt();
        }
        for(int i = 0; i < n; ++i) {
            C[i] = sc.nextInt();
        }
        //只需要对AC排序即可
        Arrays.sort(A);
        Arrays.sort(C);
        long ans = 0;
        // 遍历一遍B数组
        for(int i=0; i<n; ++i) {
            int a = test2(A, B[i]);
            // -1的情况说明没有符合的元素，则B[i]无法构成递增三元组
            // 直接continue
            if(a == -1) continue;
            int b=test1(C, B[i]);
            if(b == -1) continue;
            //这里是特别容易出错的地方。
            ans += (long)(a+1)*(n-b);
        }
        System.out.println(ans);
    }
    //从C数组中找到第一个大于target的元素的下标
    static int test1(int[] arr,int target) {
        if(arr[arr.length-1]<=target) return -1;
        int l=0;
        int r=arr.length-1;
        while(l<r) {
            int mid=(l+r)>>1;
            if(arr[mid]<=target) l=mid+1;
            else r=mid;
        }
        return l;
    }
    //从A数组中找到一个小于target的最大数
    static int test2(int[] arr,int target) {
        if(arr[0]>=target) return -1;
        int l = 0;
        int r = arr.length-1;
        while(l<r) {
            // 有符号右移一位，左边的补上符号位，正数补0，负数补1
            int mid = (l+r+1) >> 1;
            if(arr[mid] >= target) r = mid-1;
            else l=mid;
        }
        return l;
    }

}
