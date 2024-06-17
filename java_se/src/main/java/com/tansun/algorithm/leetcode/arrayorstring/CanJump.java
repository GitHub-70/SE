package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置 可以 跳跃的 最大 长度。
 *
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * 分析:
 * 1. 暴力法: 遍历数组，判断每个位置是否可以到达最后一个位置，如果可以，返回true，否则返回false。
 * 2. 动态规划:
 * 3. 贪心算法:
 *
 */
public class CanJump {

    public static void main(String[] args) {
//        int[] nums = {2,3,1,1,4};
        int[] nums = {3,2,1,0,4};
//        System.out.println(canJump(nums));
        System.out.println(canJump2(nums));
    }

    /**
     * 暴力算法
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            // 如果当前位置的值为0，则判断前面的值是否可以到达当前位置
            if (nums[i] == 0) {
                for (int j = i - 1; j >= 0; j--) {
                    // 如果前面的值可以到达当前位置，则返回false
                    if (nums[j] == i - j) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 贪心算法
     * 我们从倒数第二元素进行反向遍历，如果倒数第二元素无法到达最后一个元素，
     * 那么step++（那么压力就来到了倒数第三个元素，它相比与倒数第二个元素，需要多跳一格，这就是step++的含义）。
     * 依次类推，如果倒数第三个元素也跳不了，那么step++，交给倒数第四个元素..............
     *
     * 但如果某个元素k能够完成任务，那么这个元素前面的元素 k-1 的任务只需要跳到元素k即可。
     * （这就是step=1的含义） 最后，如果step==1，那么代表第一个元素能够完成前面元素交给它的任务，
     * 如果！=1，那么代表第一个元素不能完成前面元素交给它的任务。
     * @param nums
     * @return
     */
    public static boolean canJump2(int[] nums) {

        if (nums.length == 1 ) {
            return true;
        }

        // step表示当前位置可以到达的最远位置,主要用于判断连续为0的个数，不连续为0，则重置为1
        int step = 1;

        // 从倒数第二个位置开始遍历
        for (int i = nums.length -2 ; i >= 0; i--) {
            // 如果当前位置的值小于step，则step++, 即当前位置为0
            if (nums[i] < step){
                step++;

                // 如果nums[i] >= step, 则step=1, 如果有一位的值大于step, 则返回true
            } else {
                step = 1;
            }
        }
        return step == 1;
    }


}
