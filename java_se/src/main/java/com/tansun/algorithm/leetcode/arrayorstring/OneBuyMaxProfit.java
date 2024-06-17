package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
 */
public class OneBuyMaxProfit {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit2(prices));

    }

    /**
     * 暴力破解
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    int temp = prices[j] - prices[i];
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
        }
        return max;
    }

    /**
     * 动态规划 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
     * 记录【今天之前买入的最小值】
     * 计算【今天之前最小值买入，今天卖出的最大获利】
     * 比较【每天的最大获利】，取最大值即可
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int min = prices[0];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            // 记录【今天之前买入的最小值】
            if (prices[i] < min) {
                min = prices[i];
            }
            // 计算【今天之前最小值买入，今天卖出的最大获利】
            if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }


}
