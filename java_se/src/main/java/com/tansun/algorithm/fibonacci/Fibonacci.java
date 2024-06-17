package com.tansun.algorithm.fibonacci;

import java.util.Scanner;

/**
 * 斐波那契数
 *      0,1,1,2,3,5,8,13,21....
 *
 * 动态规划（Dynamic Programming，DP） 是一种用来解决一类 最优化问题 的算法思想。
 *      最优子结构：动态规划将一个复杂的问题分解成若干个子问题，通过综合子问题的最优解来得到原问题的最优解。（“分”与“合”体现在 状态转移方程）
 *      重叠子问题：动态规划会将每个求解过的子问题的解记录下来，这样当下一次碰到同样的子问题时，就可以直接使用之前记录的结果，而不是重复计算。
 *               （虽然动态规划使用这种方式来提高计算效率，但不能说这种做法就是动态规划的核心）
 *
 *      要记住动态规划来自于 暴力穷举，只是对暴力穷举进行了优化，我们可以先从暴力入手，从暴力里面抽取动态规划思想；
 *      从边界（临界）入手，
 *
 *    分治与动态规划——重叠子问题
 *      相同点：
 *      将问题分解成子问题，然后合并子问题的解得到原问题的解。
 *
 *      不同点：
 *      分治法解决的问题不拥有重叠子问题，解决的问题不一定是最优化问题；
 *      动态规划解决的问题拥有重叠子问题，解决的问题一定是最优化问题。
 *
 *      例子：
 *      分治法：快速排序、归并排序等。
 *
 *   贪心与动态规划——最优子结构
 *      相同点
 *      都要求原问题必须拥有最优子结构。
 *
 *      不同点
 *      贪心的计算方式类似于”自顶向下“，但是并不等待所有子问题求解完毕后再选择使用哪一个，而是通过一种策略直接选择一个子问题去求解，没被选择的子问题就不会再去求解了。
 *      动态规划的计算方式有”自顶向下“和”自底向上“两种，都是从边界开始向上得到目标问题的解。也就是说，
 *      它总是会考虑所有子问题，并选择继承能得到最优结果的那个，对暂时没有被继承的子问题，由于重叠子问题的存在，
 *      后期可能会再次考虑它们，因此还有机会成为全局最优的一部分，不需要放弃。
 */
public class Fibonacci {
    //递归循环：赋值算法。时间复杂度为O(n)
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an index for the Fibonacci number: ");
//        int index = input.nextInt();
        int index = 8;

        System.out.println("Fibonacci number at index " + index + " is " + fib(index));
    }

    public static long fib(long n) {
        long f0 = 0;
        long f1 = 1;
        long f2 = 1;

        if (n == 0)
            return f0;
        else if (n == 1)
            return f1;
        else if (n == 2)
            return f2;

        for (int i = 3; i <= n; i++) {
            f0 = f1;
            f1 = f2;
            f2 = f0 + f1;
        }
        return f2;
    }


}
