package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 前提假设：你可以假设数组是非空的，并且给定的数组总是存在多数元素
 */
import java.util.Arrays;

public class MajorityElementFinder {

    public static void main(String[] args) {
        int[] numsWithMajority = {1, 2, 3, 2, 2, 3};
        int[] numsWithoutMajority = {1, 2, 3, 4, 5};
        System.out.println(majorityElement(numsWithMajority)); // 应该输出 2
        System.out.println(majorityElement(numsWithoutMajority)); // 应该输出 -1
    }

    /**
     * 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 如果数组不存在多数元素，则返回 -1。
     * 使用 boyer-moore算法：假设你在投票选人，如果你和候选人（利益）相同，你就会给他投一票（count+1），
     * 如果不同，你就会踩他一下（count-1）。当候选人票数为0（count=0）时，就换一个候选人。
     * 但因为和你利益一样的人占比超过了一半，不论换多少次，最后留下来的都一定是个和你（利益）相同的人。
     *
     * @param nums 必须非空的整数数组
     * @return 数组中的多数元素，如果不存在多数元素则返回 -1
     * @throws IllegalArgumentException 如果输入数组为 null
     */
    public static int majorityElement(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("输入数组不能为 null");
        }

        int count = 0;
        int candidate = 0; // 候选元素

        for (int num : nums) {
            // 如果候选元素为空，则将当前元素作为候选元素
            if (count == 0) {
                candidate = num;
                count = 1;

                // 如果当前元素和候选元素相同，则计数器加1
            } else if (num == candidate) {
                count++;
                // 如果当前元素和候选元素不同，则计数器减1
            } else {
                count--;
            }
        }

        // 验证候选元素是否是多数元素
//        int occurrences = 0;
//        for (int num : nums) {
//            if (num == candidate) {
//                occurrences++;
//            }
//        }
//        return occurrences > nums.length / 2 ? candidate : -1;
        return candidate;
    }

}
