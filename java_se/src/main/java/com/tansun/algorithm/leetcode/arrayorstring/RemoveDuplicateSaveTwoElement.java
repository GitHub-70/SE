package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.Arrays;

/**
 * 删除有序数组中的重复项 II
 *
 *  给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，
 *  返回删除后数组的新长度。
 *  不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class RemoveDuplicateSaveTwoElement {

    public static void main(String[] args) {

        int[] nums = {1,1,1,2,4,4,4,4,5,8};
        System.out.println(removeDuplicatesKeepTwo(nums));
        System.out.println(Arrays.toString(nums));
    }

    /*
     * 移除数组中的重复元素,使得出现次数超过两次的元素只出现两次,返回删除后数组的新长度。
     * 1. 快慢指针
     * 2. 双指针
     */
    public static int removeDuplicatesOnlySaveTwoElement(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        /*
         * 快慢指针
         * 慢指针指向不重复的元素
         * 快指针指向重复的元素
         */
        int slow = 0;
        int fast = 1;
        int hasTwoDuplicates = 0;
        while (fast < len) {

            if (nums[slow] != nums[fast]) {
                slow++;
                hasTwoDuplicates = 0;
                // 不重复元素往前移动
                nums[slow] = nums[fast];
            } else if (nums[slow] == nums[fast] && hasTwoDuplicates == 0) {
                slow++;
                hasTwoDuplicates = 1;
                // 第二次重复元素往前移动
                nums[slow] = nums[fast];
            }
            // 快指针控制原数组遍历
            fast++;
        }
        return slow + 1;
   }

    /**
     * 优化处理--通用公式版本（keepNums）
     * 移除数组中的重复元素,使得出现次数超过两次的元素只出现两次,返回删除后数组的新长度。
     * @param nums
     * @return
     */
    public static int removeDuplicatesKeepTwo(int[] nums) {
        // 数组中的一号和二号元素肯定不用删除
        // 保留重复位数
        int keepNums = 2;
        int count = keepNums;
        for(int i = keepNums ; i < nums.length ; i++) {
            // 如果当前元素nums[i]与新数组倒数第二个元素nums[count-2]不相等
            if(nums[i] != nums[count-keepNums]) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }

}
