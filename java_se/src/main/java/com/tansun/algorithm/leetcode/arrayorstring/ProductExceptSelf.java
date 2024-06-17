package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0]
 * 提示:
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * 测试用例的答案是一个 32 位 整数可能存在溢出，所以输出结果取  modulo 109 + 7
 *
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] result = productExceptSelf(nums);
        int[] result2 = productExceptSelf2(nums);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));
    }

    public static int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        int pre = 1;
        int post = 1;
        // 前缀
        for (int i = 0; i < n; i++) {
            // 之前的元素乘积 赋值给 result
            result[i] = pre;
            // 当前元素 乘以 之前的元素,当下一轮时，直接赋值给 result[i]
            pre *= nums[i];
        }
        // 逆向遍历
        for (int i = n - 1; i >= 0; i--) {
            // 之前的元素乘积与之后的元素乘积 赋值给 result
            result[i] *= post;
            // 之后的元素的乘积 赋值给 post
            post *= nums[i];
        }

        return result;
    }

    /**
     * 双指针遍历
     * @param nums
     * @return
     */
    public static int[] productExceptSelf2(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        // 初始化，将 ans 数组全部填充为 1
        Arrays.fill(ans,1);
        int beforeSum=1;
        int afterSum=1;
        for(int i=0, j=n-1; i<n; i++, j--){
            ans[i]*=beforeSum;
            ans[j]*=afterSum;
            beforeSum*=nums[i];
            afterSum*=nums[j];
        }
        return ans;
    }

}
