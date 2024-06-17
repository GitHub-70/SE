package com.tansun.algorithm.dp;

/**
 * 动态规划问题（Dynamic Programming，简称DP）
 * 是一种在数学、计算机科学和经济学中使用的方法，用于通过将原始问题分解为相对简单的子问题来解决复杂问题。
 * 它常用于具有 重叠子问题 和 最优子结构 性质的问题
 *      1.递归+记忆化
 *      2.状态转移方程
 *
 *      https://zhuanlan.zhihu.com/p/137992855
 *
 *      算法思想：
 *          1.多个变量，用控制变量法，穷举每一个变量的组合，求出最大效应。（尽可能取减少重复计算的过程）
 *          2.多个变量，找出每一个变量的趋势，（每一个变量的时间复杂度），做对比，形成新的综合变量趋势图，
 *          来求出多个变量同时变化时，到达最终趋势时，最大边际效应。
 *          3.变量的提取，已知明显的变量提取较为容易，未知不明显的变量提取，需要大量实验，大胆的尝试与总结，
 *          与个人的经历，常识知识的储备量相关。
 */
public class DPAlgorithm {

    public static void main(String[] args) {

        int[] ints = new int[7];
        ints[0] = 0;
        ints[1] = 1;
        ints[2] = 1;
        ints[3] = 2;
        ints[4] = 3;
        ints[5] = 5;
        ints[6] = 7;
        int i = minMoney(ints, 12);
        System.out.println(i);
    }

    public static int minMoney(int[] coins, int amount) {
        int res = minDepth(coins, amount, new int[amount + 1]);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int minDepth(int[] coins, int amount, int[] map) {
        if (amount == 0)
            return 0;
        if (map[amount] != 0)// 如果计算过直接从map中取。
            return map[amount];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (amount < coins[i])
                continue;
            min = Math.min(min, minDepth(coins, amount - coins[i], map));
        }
        // 把计算的结果存储到map一份。
        map[amount] = min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min + 1;
        return map[amount];
    }


}
