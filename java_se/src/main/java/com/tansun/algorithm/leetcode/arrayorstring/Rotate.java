package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.Arrays;

/**
 * 轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数
 */
public class Rotate {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
//        int[] nums = {-1,-100,3,99};
        int k = 4;
        rotate4(nums,k); // 预期结果 [4, 5, 6, 7, 1, 2, 3]
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 旋转数组。
     * 将一个数组的所有元素向右移动k个位置，其中k是非负数。
     *
     * @param nums 待旋转的整数数组。
     * @param k 旋转的位置数。如果k大于数组长度，则取模运算以避免不必要的旋转。
     */
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        // 当数组为空或只有一个元素时，无需旋转，直接返回
        if (len == 0 || len == 1) {
            return;
        }
        // 如果k大于数组长度，取模运算，确保k在有效范围内
        if (k > len) {
            k = k % len;
        }
        // 对数组进行k次旋转操作
        for (int i = 0; i < k; i++) {
            int temp = nums[len-1]; // 保存最后一个元素
            // 将数组元素向右移动一位
            for (int j = len-1; j > 0; j--) {
                nums[j] = nums[j-1];
            }
            nums[0] = temp; // 将保存的最后一个元素放在数组的第一个位置
        }
    }


    /**
     * 旋转数组。
     * 将一个数组的所有元素向右移动k个位置，其中k是非负数。
     *
     * @param nums 待旋转的整数数组。
     * @param k 旋转的位置数。
     */
    public static void rotate2(int[] nums, int k) {
        // 创建一个临时数组，用于辅助旋转
        int[] temp = new int[nums.length];
        // 将原数组的元素根据旋转位置k，移动到临时数组相应的位置
        for (int i = 0; i < nums.length; i++) {
            temp[(i+k)%nums.length] = nums[i];
        }
        // 将临时数组的元素复制回原数组，完成旋转
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }


    /**
     * 旋转数组。
     * 将一个数组中的元素向右移动k步，然后将移动后的数组元素循环放到数组的前面。
     *
     * @param nums 待旋转的整型数组。
     * @param k 旋转的步数，k是非负整数。
     * 注意：此方法不会改变原数组的长度。
     */
    public static void rotate3(int[] nums, int k) {
        // 创建一个临时数组，用来存储移动后的元素
        int[] temp = new int[k];
        // 将原数组的最后k个元素存入临时数组
        for (int i = 0; i < k; i++) {
            temp[i] = nums[nums.length-k+i];
        }
        // 从后往前，将元素向前移动k步
        for (int i = nums.length-1; i >= k; i--) {
            nums[i] = nums[i-k];
        }
        // 将临时数组的元素放到原数组的前k个位置
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }

    }


    /**
     * 旋转数组。
     * 将一个数组向右旋转k步，其中k是非负数。
     *
     * @param nums 待旋转的整数数组。
     * @param k 旋转的步数。
     *
     *  时间复杂度：
     *          rotate4函数主要调用了3次reverse函数，每次reverse操作的时间复杂度为O(n)，其中n为数组长度。
     *          因此，总的时间复杂度为O(3n)。由于常数系数不影响大O表示法，所以该函数的时间复杂度为O(n)。
     * 空间复杂度：
     *          该代码没有使用额外的数据结构，所有操作都在原数组上进行，因此空间复杂度为O(1)
     */
    public static void rotate4(int[] nums, int k) {
        // 对k进行取模运算，确保k不超过数组长度，避免不必要的旋转
        k %= nums.length;

        // 首先翻转整个数组，这一步是将数组的后k个元素放到前面，前n-k个元素放到后面
        reverse(nums, 0, nums.length - 1);

        // 再分别翻转前k个元素和后n-k个元素，目的是将原本在前面的元素放到后面，原本在后面的元素放到前面
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * 翻转数组中指定范围的元素。
     *
     * @param nums 待翻转的整数数组。
     * @param start 翻转范围的起始索引。
     * @param end 翻转范围的结束索引。
     */
    public static void reverse(int[] nums, int start, int end) {
        // 通过交换数组元素的方式实现翻转
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


}
