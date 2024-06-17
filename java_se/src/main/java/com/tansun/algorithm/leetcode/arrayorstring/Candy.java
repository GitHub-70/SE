package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 分发糖果：
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 *      每个孩子至少分配到 1 个糖果。
 *      相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目
 *
 * 我们可以将「相邻的孩子中，评分高的孩子必须获得更多的糖果」这句话拆分为两个规则，分别处理。
 *
 * 左规则：当 ratings[i−1]<ratings[i] 时，i 号学生的糖果数量将比 i−1 号孩子的糖果数量多。
 *
 * 右规则：当 ratings[i]>ratings[i+1] 时，i 号学生的糖果数量将比 i+1 号孩子的糖果数量多
 */
public class Candy {

    public static void main(String[] args) {

//        int[] ratings = {1,0,2};// 2 1 2
//        int[] ratings = {1,2,2};// 1 2 1
//        int[] ratings = {1,2,2,3};// 1 2 1 2
//        int[] ratings = {1,2,2,1};// 1 2 2 1
//        int[] ratings = {1,2,2,2,3};// 1 2 1 1 2
//        int[] ratings = {1,3,2,2,1}; // 1 2 1 2 1
//        int[] ratings = {1,6,10,8,7,3,2}; // 1 2 5 4 3 2 1
        int[] ratings = {58,21,72,77,48,9,38,71,68,77,82,47,25,94,89,54,26,54,54,99,64,71,76,63,81,82,60,64,29,51,87,87,72,12,16,20,21,54,43,41,83,77,41,61,72,82,15,50,36,69,49,53,92,77,16,73,12,28,37,41,79,25,80,3,37,48,23,10,55,19,51,38,96,92,99,68,75,14,18,63,35,19,68,28,49,36,53,61,64,91,2,43,68,34,46,57,82,22,67,89};
//        int[] ratings = {1,2,87,87,87,2,1}; // 1 2 3 1 3 2 1
//        int[] ratings = {1, 2, 87, 87, 67, 87, 87, 2, 1}; // 1 2 3 2  1  2 3 2 1
//        int[] ratings = {1, 2, 3, 87, 87, 67, 57, 87, 87, 2, 1}; // 1 2 3 4 3 2 1  2 3 2 1
        System.out.println(candy(ratings));
        System.out.println(candy2(ratings));

    }

    /**
     * 贪心算法
     *      这在leetcode上是一道困难的题目，其难点就在于贪心的策略，如果在考虑局部的时候想两边兼顾，就会顾此失彼。
     *
     * 那么本题我采用了两次贪心的策略：
     *      一次是从左到右遍历，只比较右边孩子评分比左边大的情况。
     *      一次是从右到左遍历，只比较左边孩子评分比右边大的情况。
     *      这样从局部最优推出了全局最优，即：相邻的孩子中，评分高的孩子获得更多的糖果
     *
     *      时间复杂度: O(n)
     *      空间复杂度: O(n)
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] nums = new int[n];
        int sum = 0;
        // 正向遍历，
        for (int i = 0; i < n; i++) {
            // 递增区间，对应值为前一位+1，否则为1(之后逆向遍历会矫正其值)
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                nums[i] = nums[i - 1] + 1;
            } else {
                nums[i] = 1;
            }
        }

        // 逆向遍历
        for (int i = n - 1; i >= 0; i--) {
            // 逆向递增（后一位小于前一位）
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                // 逆向遍历的后一位+1与正向遍历的 i 位比较 取大值，为 i 位的值
                nums[i] = Math.max(nums[i + 1] + 1, nums[i]); // {1,6,10,8,7,3,2};  1 2 5 4 3 2 1
            } else {
                nums[i] = nums[i];
            }
        }

        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        return sum;
    }

    /**
     * 贪心算法
     * 有Bug,前后变量，两者各自为算，比最终的大值，会有遗漏（各自为算，并不精准）
     * @param ratings
     * @return
     */
    public static int candy2(int[] ratings) {
        int n = ratings.length;
        int preTotalCandy = 1;
        int postTotalCandy = 1;
        int maxContinuousPointer = 0; // 最大连续（单调）指针

        for (int i = 1; i < n; i++) {
            // 单调递增
            if (ratings[i] > ratings[i - 1]) {
                if (maxContinuousPointer > 0){
                    maxContinuousPointer++;

                } else if (maxContinuousPointer <= 0){
                    maxContinuousPointer = 2;
                }
                preTotalCandy += maxContinuousPointer;

                // 单调递减
            } else if ((ratings[i] < ratings[i - 1])) {
                if (maxContinuousPointer < 0){
                    maxContinuousPointer--;

                } else if (maxContinuousPointer == 0){
                    maxContinuousPointer = -2;

                } else if (maxContinuousPointer > 0){
                    maxContinuousPointer = -1;
                }
                preTotalCandy -= maxContinuousPointer;
                // 相等，重置单调区间
            } else if ((ratings[i] == ratings[i - 1])) {
                maxContinuousPointer = 0;
                preTotalCandy += 1;
            }
        }
        // 逆向遍历
        maxContinuousPointer = 0;
        for (int j = n - 2; j >= 0; j--) {// 1,2,3,5,4,3,2,1,4,3,2,1,3,2,1,1,2,3,4
            // 逆向单调递增
            if (ratings[j] > ratings[j + 1]) {
                if (maxContinuousPointer > 0){
                    maxContinuousPointer++;

                } else if (maxContinuousPointer <= 0){
                    maxContinuousPointer = 2;
                }
                postTotalCandy += maxContinuousPointer;

                // 逆向单调递减
            } else if ((ratings[j] < ratings[j + 1])) {
                if (maxContinuousPointer < 0){
                    maxContinuousPointer--;

                } else if (maxContinuousPointer == 0){
                    maxContinuousPointer = -2;

                } else if (maxContinuousPointer > 0){
                    maxContinuousPointer = -1;
                }
                postTotalCandy -= maxContinuousPointer;
                // 相等，重置单调区间
            } else if ((ratings[j] == ratings[j + 1])) {
                maxContinuousPointer = 0;
                postTotalCandy += 1;
            }
        }
        return preTotalCandy > postTotalCandy ? preTotalCandy : postTotalCandy;
    }

}
