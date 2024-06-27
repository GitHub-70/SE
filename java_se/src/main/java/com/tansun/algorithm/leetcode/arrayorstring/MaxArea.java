package com.tansun.algorithm.leetcode.arrayorstring;

public class MaxArea {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
        System.out.println(maxArea2(height));
    }


    /**
     * 暴力法
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                if (area > max) {
                    max = area;
                }
            }
        }
        return max;
    }

    /**
     * 双指针法
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int max = 0;
        // 求两个柱子之间的下标差*两个柱子的min高度
        for (int i = 0, j = height.length - 1; i < j;) {
            int area = Math.min(height[i], height[j]) * (j - i);
            if (area > max) {
                max = area;
            }
            // 共同维护最高的柱子
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}
