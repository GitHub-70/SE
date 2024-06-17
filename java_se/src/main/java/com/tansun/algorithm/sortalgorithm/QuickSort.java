package com.tansun.algorithm.sortalgorithm;

/**
 * https://zhuanlan.zhihu.com/p/508663114#:~:text=%E6%9C%80%E8%AF%A6%E7%BB%86%E7%9A%84%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95%E2%80%94%E2%80%94%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%201%201.%E4%BB%8B%E7%BB%8D%20%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%EF%BC%88Quick%20Sort%EF%BC%89%E6%98%AF%E4%BB%8E%20%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95%20%E6%BC%94%E5%8F%98%E8%80%8C%E6%9D%A5%E7%9A%84%EF%BC%8C%E5%AE%9E%E9%99%85%E4%B8%8A%E6%98%AF%E5%9C%A8%20%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F,%2F%2F%20%E9%80%92%E5%BD%92%E7%BB%93%E6%9D%9F%E6%9D%A1%E4%BB%B6%EF%BC%9AstartIndex%E5%A4%A7%E4%BA%8E%E7%AD%89%E4%BA%8EendIndex%E7%9A%84%E6%97%B6%E5%80%99%20...%204%204.%20%E7%AE%97%E6%B3%95%E8%AF%84%E4%BC%B0%20%E6%97%B6%E9%97%B4%E5%A4%8D%E6%9D%82%E5%BA%A6%20
 *
 * 实现思想：
 * 选择基准元素：从待排序数组中选择一个元素作为基准元素（通常选择中间元素），将这个基准元素暂时放在一边。
 * 分割阶段：将数组中小于基准元素的元素放在基准元素的左边，将大于基准元素的元素放在基准元素的右边，
 * 同时基准元素所在的位置也确定了。
 * 递归排序：对基准元素左右两部分分别递归地进行快速排序。
 *
 * 过程步骤：
 * 选择基准元素：选择数组中间元素作为基准元素。
 * 分割数组：设定两个指针i和j，分别指向数组的起始和末尾。移动指针i，直到找到一个大于等于基准元素的元素；
 *          移动指针j，直到找到一个小于等于基准元素的元素。交换这两个元素的位置，继续移动指针i和j，直到i >= j。
 * 递归排序：对基准元素左右两部分分别递归地进行快速排序。
 *
 * 时间复杂度：
 * 平均时间复杂度：O(nlogn)
 * 最坏时间复杂度：O（n^2）（当选择的基准元素不合适时）
 * 空间复杂度：O(logn)（递归调用栈的深度）
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        quickSort(arr, 0, arr.length - 1);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /**
     * 快速排序
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // 左右指针在一个位置时，结束
        if (left == right) {
            return;
        }

        // 基数位置
        int middle = left + (right - left) / 2;
        int pivot = arr[middle];

        // 左右指针
        int i = left, j = right;
        while (i <= j) {
            // 左指针小于基数，左指针右移，直到找到一个大于等于基准元素的元素
            while (arr[i] < pivot) {
                i++;
            }

            // 右指针小于基数，右指针左移，直到找到一个小于等于基准元素的元素
            while (arr[j] > pivot) {
                j--;
            }

            // 将大于基数的数 放在基数右边，小于基数的数，放在基数左边，即找到的两数交换位置，同时移动指针，直到这一轮结束
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // 左边的数，从新排序，进行下一轮，从新找基数
        if (left < j) {
            quickSort(arr, left, j);
        }

        // 右边的数，从新排序，进行下一轮，从新找基数
        if (right > i) {
            quickSort(arr, i, right);
        }
    }


}

