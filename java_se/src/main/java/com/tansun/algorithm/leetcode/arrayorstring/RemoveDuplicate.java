package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.Arrays;

/**
 * 删除有序数组中的重复项
 */
public class RemoveDuplicate {

    public static void main(String[] args) {

        int[] nums = {1,1,2,4,4,5,8,8};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    /*
     * 移除数组中的重复元素, 返回不重复元素的个数
     * 1. 快慢指针
     * 2. 双指针
     */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        /*
         * 快慢指针
         * 慢指针指向不重复的元素
         * 快指针指向重复的元素
         */
        int slow = 0;
        int fast = 1;
        while (fast < len) {

            if (nums[slow] != nums[fast]) {
                slow++;
                // 不重复元素往前移动
                nums[slow] = nums[fast];
            }
            // 快指针控制原数组遍历
            fast++;
        }
        return slow + 1;
   }
}
