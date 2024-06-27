package com.tansun.algorithm.sort;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        sort(arr);
        System.out.println("排序后的数组：" + java.util.Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        if (len < 2) {
            return;
        }
        int mid = len / 2;
        int[] left = new int[mid];
        int[] right = new int[len - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < len; i++) {
            right[i - mid] = arr[i];
        }
        sort(left);
        sort(right);
        mergeSort(arr, left, right);
    }
    public static void mergeSort(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
