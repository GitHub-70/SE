package com.tansun.algorithm.sortalgorithm;

/**
 * 归并排序（Merge Sort）
 * 思想与步骤：
 * 分治法：将待排序数组分为两个子数组，分别对这两个子数组进行排序，然后合并这两个已排序的子数组。
 * 递归：重复上述步骤，直到子数组的长度为1，即子数组已经有序。
 * 合并：将两个有序子数组合并成一个有序数组。
 *
 * 步骤：
 * 分解：将数组分为两个子数组，直到每个子数组只有一个元素。
 * 合并：按照顺序合并两个有序子数组，形成一个新的有序数组。
 * 递归：重复以上步骤，直到整个数组有序。
 *
 * 优点：
 * 稳定性：归并排序是一种稳定的排序算法，相同元素的相对位置不会改变。
 * 适用性：适用于大规模数据集合，效率稳定。
 * 并行性：归并排序可以很容易地并行化实现。
 *
 * 缺点：
 * 空间复杂度：归并排序需要额外的空间来存储临时数组，空间复杂度为O(n)。
 * 时间复杂度：归并排序的时间复杂度为O(n log n)，相对于快速排序，其常数项较大。
 *
 * 时间复杂度：
 * 最好情况：O(n log n)
 * 最坏情况：O(n log n)
 * 平均情况：O(n log n)
 * 总体来说，归并排序是一种高效且稳定的排序算法，特别适用于对大规模数据集合进行排序。
 * 虽然空间复杂度较高，但其稳定性和可预测的时间复杂度使其成为常用的排序算法之一。
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        mergeSort(arr, 0, arr.length - 1);

        for (int num : arr) {
            System.out.print(num + " ");
        }

    }


    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;// 左边元素个数 小于等于 右边元素个数

            mergeSort(arr, left, mid);// 得到左边排序后的新数组
            mergeSort(arr, mid + 1, right); // 得到右边排序后的新数组

            merge(arr, left, mid, right);
        }
    }

    /**
     * 将原数组拆分成两个数组，比较两个数组值，将小的值放入一个新的数组
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // 准备左右两个数组
        int[] L = new int[n1];
        int[] R = new int[n2];

        // 左边数组元素
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }

        // 右边数组元素
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        // 使用三个指针 i、j 和 k 分别控制左右两个子数组和原数组的位置
        int i = 0, j = 0;
        int k = left;

        // 在一个 while 循环中，比较 L 和 R 中的元素，将较小的元素放入原数组中，并移动相应的指针。
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // 最后，将剩余的元素依次放入原数组中，完成一轮排序
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


}

