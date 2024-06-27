package com.tansun.algorithm.sort;

import java.util.Arrays;

public class InsertSort {


    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序：左端第一个数字为已经完成排序的数字，其他数字为未排序的数字。
     *          然后从左到右依次将未排序的数字插入到已排序的数字中。
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int insertIndex = i - 1;
            // 说明：当已排序的数字大于未排序的数字时，将已排序的数字后移
            // 找到已排序的数字小于未排序的数字的位置
            while (insertIndex >= 0 && arr[insertIndex] > temp ) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 插入未排序的数字
            arr[insertIndex + 1] = temp;
        }
    }
}
