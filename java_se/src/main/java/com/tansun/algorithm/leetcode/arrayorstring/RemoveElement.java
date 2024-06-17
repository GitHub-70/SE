package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.Arrays;
import java.util.List;

/**
 * 题目：
 *      给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素,
 *      然后返回 nums 中与 val 不同的元素的数量。
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
 * 返回 k。
 *
 * 示例 2：
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3,_,_,_]
 * 解释：你的函数应该返回 k = 5，并且 nums 中的前五个元素为 0,0,1,3,4。
 */
public class RemoveElement {


    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        System.out.println(removeElement(nums, 3));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 从数组中移除指定元素的所有实例，并返回移除后数组的新长度。
     * 注意：原数组不会被改变，而是通过移动元素的方式在原数组前面形成了一个新的有效数组。
     *
     * @param nums 指定的整型数组，其中的元素可能会包含要移除的目标值。
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {

        if (nums == null || nums.length == 0){
            return 0;
        }

        int pre = 0; // pre指针 用于记录前 pre 个元素都不等于 val

        for (int i = 0; i < nums.length; i++) {
            // 不等于 val 往前移动
            if (nums[i] != val){
                nums[pre] = nums[i];
                pre++;
            }
        }
        return pre;
    }
}
