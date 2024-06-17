package com.tansun.algorithm.sortalgorithm;

/**
 * 冒泡排序：
 * 冒泡排序是一种交换排序算法，它通过相邻元素的比较和交换来将最大（或最小）的元素逐步移动到最后。
 * 在每一轮遍历中，都会比较相邻的两个元素，如果它们的顺序错误就交换它们。
 * 冒泡排序的时间复杂度为 O(n^2)其中n是要排序的元素个数。它的性能不是很好，特别是对于大型数据集。
 * 选择排序：
 * 选择排序是一种选择排序算法，它通过每一轮遍历选择未排序部分的最小（或最大）元素，然后将其放到已排序部分的末尾。
 * 在每一轮遍历中，都会找到未排序部分的最小（或最大）元素，并与未排序部分的第一个元素交换位置。
 * 选择排序的时间复杂度也为 O(n^2)，但是在实践中通常比冒泡排序快一些，因为交换的次数更少。
 * 因此，选择排序和冒泡排序的主要区别在于它们的实现方式和性能特点。选择排序在某些情况下可能比冒泡排序更快，
 * 但它们都不是高效的排序算法。在实际应用中，更常用的排序算法是快速排序、归并排序、插入排序等。
 */
public class DubbleSort {


    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90, 5};
        bubbleSort(arr);
        System.out.print("Sorted array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        // 外层控制最大循环次数 n-1 次，相邻位置，两两交换位置，如若原数组中的最小数排在最后一位，最大遍历次数为 n-1次
        /* 调整最外层变量次数，观察 5这个数 位置的变化位置 */
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


}
