package com.tansun.algorithm.leetcode.doublepointer;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-2,0,0,2,2};
        int[] nums = {3, 0, -2, -1, 1, 2};
        System.out.println(threeSum(nums));
        System.out.println(threeSum2(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        /*
         * 1. 排序
         * 2. 遍历数组，固定一个数，然后使用双指针法
         * 3. 双指针法，使用左右指针，左指针指向固定数后面的第一个数，右指针指向数组的最后一个数
         * 4. 如果固定数+左指针+右指针的和大于0，则右指针左移；如果固定数+左指针+右指针的和小于0，
         *    则左指针右移；如果固定数+左指针+右指针的和等于0，则将结果添加到结果集中，然后左指针右移，
         *    右指针左移
         */

        Arrays.sort(nums);
        int n = nums.length;
        if (n < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> totalGroup = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            // 外层去重
            int x = nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (x + nums[i + 1] + nums[i + 2] > 0) break; // 优化一
            if (x + nums[n - 2] + nums[n - 1] < 0) continue; // 优化二

            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                // 内层去重,因为是两个指针同时移动，第一个元素必须判断，否则可能漏掉元素
                if (totalGroup.size() > 0
                        && totalGroup.get(totalGroup.size() - 1).get(0) == nums[i]
                        && totalGroup.get(totalGroup.size() - 1).get(1) == nums[left]
                        && totalGroup.get(totalGroup.size() - 1).get(2) == nums[right]) {
                    left++;
                    right--;
                    continue;
                }
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                }
                if (sum < 0) {
                    left++;
                }
                if (sum == 0) {
                    // 创建固定大小的List
                    List<Integer> threeGroup = Collections.unmodifiableList(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    totalGroup.add(threeGroup);
                }
            }
        }
        return totalGroup;
    }

    /**
     * 优化版本
     * 时间复杂度：O(n^2)，其中n是数组长度。主要消耗在于排序操作（O(n log n)）以及之后的双层循环结构，
     * 虽然内层循环可能会提前终止，但最坏情况下仍为O(n)。整体上，排序是主要的时间消耗来源。
     *
     * 空间复杂度：O(n)，需要额外的空间存储排序后的数组以及结果列表。
     * 结果列表最多可能存储n/3个三元组（考虑到最优情况下，每个元素仅能参与一个三元组的构成，
     * 例如大量重复的0组成的情况），因此空间复杂度为O(n)。此外，还有一些变量和临时列表的存储，
     * 但这些相对于n来说都是常数级别，不影响整体空间复杂度的评估。
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> totalGroup = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            // 外层去重
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) continue;
            if (x + nums[i + 1] + nums[i + 2] > 0) break; // 优化一
            if (x + nums[n - 2] + nums[n - 1] < 0) continue; // 优化二

            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                }
                if (sum < 0) {
                    left++;
                }
                if (sum == 0) {
                    List<Integer> threeGroup = Collections.unmodifiableList(Arrays.asList(nums[i], nums[left], nums[right]));
                    totalGroup.add(threeGroup);
                    for (++left; left < right && nums[left] == nums[left - 1]; ++left); // 跳过重复数字
                    for (--right; right > left && nums[right] == nums[right + 1]; --right); // 跳过重复数字
                }
            }
        }
        return totalGroup;

    }

}
