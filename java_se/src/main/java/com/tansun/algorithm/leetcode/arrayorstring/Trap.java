package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class Trap {

    public static void main(String[] args) {

//        int[] ratings = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] ratings = {4, 2, 0, 3, 2, 5};
        System.out.println(trap(ratings));
        System.out.println(trap3(ratings));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int left = 0;
        int sum = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] < height[left]) {
                continue;
            } else {
                // 此时的柱子比left柱子高，且两柱子之间有间隙可接水
                if (i - left > 1) {
                    sum += height[left] * (i - left - 1);
                    for (int j = left + 1; j < i; j++) {
                        sum -= height[j]; // 减掉中间矮柱子占用空间
                    }
                }
                left = i; // 更换left柱子
            }
        }

        // left下标为最高的柱子,可用来减少循环数量了
        int right = height.length - 1;
        for (int i = right - 1; i >= left; i--) {
            if (height[i] < height[right]) {
                continue;
            } else {
                // 此时的柱子比right柱子高，且两柱子之间有间隙可接水
                if (right - i > 1) {
                    sum += height[right] * (right - i - 1);
                    for (int j = i + 1; j < right; j++) {
                        sum -= height[j]; // 减掉中间矮柱子占用空间
                    }
                }
                right = i; // 更换right柱子
            }
        }
        return sum;

    }

    /**
     * 双指针
     * 该函数用于计算一个数组中由相邻元素形成的“水槽”中可以容纳的水量。使用双指针从数组两端向中间遍历，
     * 同时维护一个minHeight变量表示当前左右指针之间的最小高度。遍历过程中，
     * 如果左指针或右指针所指的高度小于等于minHeight，则该位置的高度为水槽的底部，
     * 可以计算出容纳的水量并累加到total上。然后将指针向内移动，继续遍历。最后返回total作为结果。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int total = 0;
        int left = 0;
        int right = height.length - 1;
        int minHeight = 0;
        while (left < right) {
            int leftNum = height[left];
            if (leftNum <= minHeight) {
                total = total + (minHeight - leftNum);
                left++;
                continue;
            }
            int rightNum = height[right];
            if (rightNum <= minHeight) {
                total = total + (minHeight - rightNum);
                right--;
                continue;
            }
            minHeight = Math.min(leftNum, rightNum);
        }
        return total;
    }

    /**
     * 单调栈
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *      初始化变量ans为0，用于累计雨水总量。创建一个单调栈stack，用于存储柱子的索引。
     *      获取数组height的长度n。
     *      遍历数组，对每个柱子进行处理：
     *      当栈不为空且当前柱子的高度大于栈顶柱子的高度时，说明栈顶柱子的顶部可以形成雨水，进行如下操作：
     *      弹出栈顶柱子的索引top。
     *      如果栈为空，说明当前柱子是最高的，跳出循环。
     *      获取栈顶柱子的索引left。
     *      计算当前柱子与栈顶柱子之间的宽度currWidth。
     *      计算当前柱子与栈顶柱子之间高度最小值currHeight。
     *      累加宽度与高度的乘积到ans中。
     *      将当前柱子的索引i入栈。
     *      遍历结束后，返回ans作为结果。
     * @param height
     * @return
     */
    public static int trap3(int[] height) {
        int ans = 0;
        // 用于存储柱子的索引
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            // 当栈不为空且当前柱子的高度大于栈顶柱子的高度时，说明栈顶柱子的顶部可以形成雨水
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 弹出栈顶柱子的索引top。
                int top = stack.pop();
                // 如果栈为空，说明当前柱子是最高的，跳出循环
                if (stack.isEmpty()) {
                    break;
                }
                // 获取栈顶柱子的索引left。
                int left = stack.peek();
                int currWidth = i - left - 1;
                // 计算当前柱子与栈顶柱子之间高度最小值currHeight
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

}
