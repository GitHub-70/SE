package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。可多次购买，你在任何时候 最多 只能持有 一股 股票。
 * 你也可以先购买，然后在 同一天 出售。
 *
 * 返回 你能获得的 最大 利润
 */
public class MoreBuyMaxProfit {


    public static void main(String[] args) {

        int [] prices = {7,1,5,3,6,4};
//        int [] prices = {1,2,3,4,5};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfitMultipleBuys(prices));

    }

    /**
     * 想象一下股票的走势图：
     * 在一个最大的上升趋势区间找出买入卖出时机，求单个最大上升区间的最大收益，最后将整个多个的最大上升区间的最大收益进行累加，就是整个最大的收益。问题就变为，最大上升区间的最大收益买入卖出时机。
     * 可以想象一下，一个有两个上升区间是这样的，如下：
     * 【7,1,5,5,7,4】，这个最大上升区间是【1,5,5,7】，其最大收益是【5-1,7-5】求和，是一个连续的上升区间，最大收益也就相当于【7-1】。买入时机，连续上升区间的最小值，卖出时机，连续上升区间的最大值。
     * 【7,1,5,3,7,4】，这个最大上升区间是【1,5,3,7】，其最大收益是【5-1,7-3】求和，是一个非连续的上升区间。买入时机，连续上升区间的最小值，卖出时机，连续上升区间的最大值。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices){
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 连续上升区间的最大值
            int curMax = 0;
            // 遇到了下降区间，当前的值就是下一个上升区间的最小值，最大值就等于上一个值减去最小值
            if (prices[i-1] > prices[i]){
                curMax = prices[i-1] - min < 0 ? 0 :prices[i-1] - min;
                min = prices[i];

                // 如果整个区间是个连续的上升空间
            } else if (i == prices.length-1){
                // 最后一位就是这个区间的最大值
                curMax = prices[i] - min;
            }

            // 多个上升区间的累计和
            max += curMax;
        }
        return max;
    }

    /**
     * 官方解法
     * 动态规划 解法
     *
     * 时间复杂度：O(n)，其中 n 为数组的长度。一共有 2n 个状态，每次状态转移的时间复杂度为 O(1)，
     * 因此时间复杂度为 O(2n)=O(n)。
     *
     * 空间复杂度：O(n)。我们需要开辟 O(n) 空间存储动态规划中的所有状态。如果使用空间优化，
     * 空间复杂度可以优化至 O(1)。
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 官方解法
     * 贪心解法
     *
     * 时间复杂度：O(n)，其中 n 为数组的长度。
     *
     * 空间复杂度：O(1)。
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }


    public static int maxProfitMultipleBuys(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }

        int[] buy = new int[n];
        int[] sell = new int[n];

        buy[0] = -prices[0];
        sell[0] = 0;

        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }

        return sell[n - 1];
    }
}
