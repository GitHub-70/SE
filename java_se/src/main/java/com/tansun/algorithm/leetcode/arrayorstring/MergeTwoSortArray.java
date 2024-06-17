package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * 合并两个递增有序的数组，通过修改nums1数组来存储合并后的结果，确保合并后的数组仍保持有序
 */
public class MergeTwoSortArray {

    public static void main(String[] args) {

        int[] nums1 = {1,2,3,7,0,0,0};
        int[] nums2 = {2,5,6};
//        merge(nums1,4,nums2,3);
        merge2(nums1,4,nums2,3);
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }

    }

    /**
     * 合并两个有序数组，通过修改nums1数组来存储合并后的结果，确保合并后的数组仍保持有序
     * @param nums1  nums1是长度为m的第一个数组
     * @param m
     * @param nums2  nums2是长度为n的第二个数组
     * @param n
     *
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        m--;
        for (n = n - 1; n >= 0; ) {
            if (m < 0 || nums2[n] > nums1[m]) {
                nums1[m + n + 1] = nums2[n];
                n--;
            } else {
                nums1[m + n + 1] = nums1[m];
                nums1[m] = 0;
                m--;
            }
        }
    }

    /**
     * 优化版
     *
     * 合并两个有序数组，通过修改nums1数组来存储合并后的结果，确保合并后的数组仍保持有序
     * @param nums1  nums1是长度为m的第一个数组
     * @param m
     * @param nums2  nums2是长度为n的第二个数组
     * @param n
     *
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        // 预先检查边界条件，防止数组越界
        if (m < 0 || n < 0 || (m + n) > nums1.length) {
            throw new IllegalArgumentException("Input arrays are out of bound.");
        }

        // 从数组末尾开始合并，保证合并后的数组仍然有序
        m--; // 减一是为了处理下标从0开始的情况
        n--; // 同样减一是为了直接与nums1进行比较，避免额外的判断
        int tail = m + n + 1; // 合并数组的尾部索引

        while (n >= 0) {
            if (m >= 0 && nums1[m] > nums2[n]) {
                // 如果nums1中还有元素且元素大于nums2当前元素，则将nums1中的元素放到合并数组的尾部
                nums1[tail] = nums1[m];
                m--;
            } else {
                // 否则将nums2中的元素放到合并数组的尾部
                nums1[tail] = nums2[n];
                n--;
            }
            tail--; // 更新合并数组的尾部索引
        }
    }
}
