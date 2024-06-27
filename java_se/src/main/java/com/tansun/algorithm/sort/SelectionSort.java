package com.tansun.algorithm.sort;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 23, 5, 235, 257, 8, 9, 10};
        System.out.println(Arrays.toString(selectionSort(arr)));
    }

    /**
     * 选择排序
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // 选取最小值放到i位置
                if (arr[i] > arr[j]) {// 注意和冒泡排序的区别，这里是i和j比较。
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
