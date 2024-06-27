package com.tansun.algorithm.leetcode.doublepointer;

import com.google.j2objc.annotations.Weak;

import java.util.Arrays;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        int[] result2 = twoSum2(nums, target);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));
    }

    /**
     * 暴力算法
     *
     * @param numbers
     * @param target
     * @return
     */

    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers.length - 1; j > i; j--) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[0];
    }

    /**
     * 双指针
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *      使用双指针的实质是缩小查找范围。那么会不会把可能的解过滤掉？
     *      假设 numbers[i]+numbers[j]=target 是唯一解，其中 0≤i<j≤numbers.length−1 。
     *      初始时两个指针分别指向下标 0 和下标 numbers.length−1，左指针指向的下标小于或等于 i，
     *      右指针指向的下标大于或等于 j。除非初始时左指针和右指针已经位于下标 i 和 j，
     *      否则一定是左指针先到达下标 i 的位置或者右指针先到达下标 j 的位置。
     *
     *      如果左指针先到达下标 i 的位置，此时右指针还在下标 j 的右侧，sum>target，因此一定是右指针左移，
     *      左指针不可能移到 i 的右侧。
     *
     *      如果右指针先到达下标 j 的位置，此时左指针还在下标 i 的左侧，sum<target，因此一定是左指针右移，
     *      右指针不可能移到 j 的左侧。
     *
     * 由此可见，在整个移动过程中，左指针不可能移到 i 的右侧，右指针不可能移到 j 的左侧，
     * 因此不会把可能的解过滤掉。由于题目确保有唯一的答案，因此使用双指针一定可以找到答案
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int len = numbers.length - 1;
        while (left < len) {
            int sum = numbers[left] + numbers[len];
            if (sum == target) {
                return new int[]{left + 1, len + 1};
            } else if (sum < target) {
                left++;
            } else {
                len--;
            }
        }

        return new int[0];
    }

    /**
     * 二分查找法(有序集合)
     * 时间复杂度O(nlogn)
     * 空间复杂度O(1)
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 中位数查找
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum4(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (numbers[mid] == target - numbers[left]) {
                return new int[]{left + 1, mid + 1};
            } else if (numbers[mid] > target - numbers[left]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

}
