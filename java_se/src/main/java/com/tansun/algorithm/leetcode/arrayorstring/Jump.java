package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * <p>
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * <p>
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * <p>
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 */
public class Jump {

    public static void main(String[] args) {
//        int[] nums = {2,3,1,1,4};
//        int[] nums = {3,2,1,0,4};
//        int[] nums = {1, 2, 3, 3, 5, 6, 7}; // 3
//        int[] nums = {1,1,1,2,1}; // 4
//        int[] nums = {3,4,3,2,5,4,3}; // 3
//        int[] nums = {7,0,9,6,9,6,1,7 ,9,0,1,2,9,0,3};// 2
//        int[] nums = {2,9  ,6,5,7,0,7,2,7,9,  3,2,2,5,7,8,1,6,6,  6,3,5,2,2,6,  3};// 5
        int[] nums = {1,2,1,1,1};// 3
        System.out.println(leastJumpTimes(nums));

    }

    /**
     * 贪心算法（局部最优-->全局最优）
     *
     * @param nums
     * @return
     */
    private static int leastJumpTimes(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (nums[0] >= n - 1) {
            return 1;
        }

        int jumpTimes = 1;
        int curBestValue = nums[0];  // 当前最优值 = 当前值+当前索引下标
        int curBestValueIndex = 0;
        int nextMaxStepIndex = nums[0];

        // 求出到达最后一位的最少跳跃次数
        // 判断当前元素能到达的最远距离，选取这一轮的最大元素，作为下一轮能到达的最远距离
        // {3,3,2,3,1,2,7,5,5,2}
        // {3,2,3,3,1,2, 7,5,5,2}
        for (int i = 1; i < n; i++) {

            // 刚开始进入下一轮，并且当前元素 能跳完最后的步数，跳跃次数+2
            if (i > nextMaxStepIndex && nums[i] >= n - curBestValueIndex - 1) {

                return jumpTimes + 2;

                // 刚开始进入下一轮，当前元素还不能跳完最后的步数，重置下一轮最优值，维护下一轮的终止位置，跳跃次数+1
            } else if (i > nextMaxStepIndex) {
                curBestValue =  1;
                nextMaxStepIndex = nums[curBestValueIndex] + curBestValueIndex;
                jumpTimes++;
            }

            // 当前最可能跳去的位置
            if (nums[i]+i > curBestValue) {
                curBestValue = nums[i]+i;
                curBestValueIndex = i;
            }

            // 当前最可能 jump 去的位置的一个元素
            if (nums[i] >= n - i - 1) {
                return ++jumpTimes;
            }

        }
        return jumpTimes;
    }

    /**
     * 官方解法
     * 贪心算法（局部最优-->全局最优）
     * @param nums
     * @return
     */
    private static int leastJumpTimes2(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            // 更新最远可以到达的位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 当到达这一轮最远距离时，更新下一轮最远位置，并且步数+1
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
