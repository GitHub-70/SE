package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.Arrays;

/**
 * 28. 找出字符串中第一个匹配项的下标
 * 找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的
 * 第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 * 示例 1：
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 *
 * 示例 2：
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 */
public class StrStr {

    public static void main(String[] args) {
//        String haystack = "sadbutsad";
//        String needle = "sad";
        String haystack = "mississippi";
        String needle = "issip";
        System.out.println(strStr(haystack, needle));
        System.out.println(strStr2(haystack, needle));
        System.out.println(strStr3(haystack, needle));
        System.out.println(Arrays.toString(buildPrefixTable("aaaaaaaaa".toCharArray())));
        System.out.println(Arrays.toString(buildPrefixTable("abcabdabd".toCharArray())));
        System.out.println(Arrays.toString(buildPrefixTable("abcabdabc".toCharArray())));
        System.out.println(Arrays.toString(buildPrefixTable("abcabcabc".toCharArray())));// 匹配串具有良好的局部对称性

    }

    /**
     * 暴力匹配
     * 时间复杂度 O(m*n)
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                // 之前匹配上j位，现在就减去 j 位，再此基础上+1，进行下一轮匹配
                i = i - j + 1;
                j = 0;
            }
        }
        return j == needle.length() ? i - j : -1;
    }

    /**
     * 滑动窗口
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr2(String haystack, String needle) {
        //构建长度与needle相等的滑动窗口
        int left = 0, right = needle.length() - 1;
        //不断移动滑动窗口，窗口的子串与目标子串进行匹配
        while (right <= haystack.length() - 1) {
            if (haystack.substring(left, right + 1).equals(needle)) return left;
            left++;
            right++;
        }
        return -1;
    }

    /**
     * KMP算法
     *  KMP算法的关键在于利用已经得到的部分匹配信息，避免了主串指针的回溯，从而减少了不必要的比较，
     *  达到在最坏情况下时间复杂度为O(m+n)的高效匹配。
     *
     *  前缀表（Partial Match Table，也称作失配表或next数组）：KMP算法的核心在于构建一个前缀表。
     *  这个表记录了模式串中所有子串的最长相同 前缀和后缀的长度。换句话说，如果模式串为P，
     *  那么next[j]表示P的前j个字符组成的子串中，最大的 既是前缀也是后缀的长度。
     *  前缀表的构建是一个自洽的过程，通过递推方式完成。
     *
     *  匹配过程：在匹配过程中，当主串中的字符与模式串中的字符不相等时，不是简单地将主串的指针回退一位再进行比较，
     *  而是根据当前失配位置在前缀表中的值，直接将模式串滑动到相应位置继续比较。这样做的依据是，
     *  既然我们已经知道模式串中某个前缀和后缀是相同的，那么下次匹配时，可以直接利用这一信息，
     *  避免重复比较那些已经确认匹配的字符。
     *
     *  时间复杂度：O(m+n) 其中m为主串的长度，n为模式串的长度。
     *  空间复杂度：O(n)
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr3(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        char[] chars = needle.toCharArray();
        int[] prefixTable = buildPrefixTable(chars);
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0){
                    i++;
                } else {
                    j = prefixTable[j-1];
                }

            }
        }
        return j == needle.length() ? i - j : -1;
    }

    /**
     * 构建KMP算法的前缀表
     * @param pattern
     * @return 前缀表
     */
    public static int[] buildPrefixTable(char[] pattern) {
        int[] prefixTable = new int[pattern.length];
        prefixTable[0] = 0; // 初始化第一个元素为0

        int patternIndex = 0; // 公共前后缀索引

        // 从第二个字符开始构建前缀表
        for (int i = 1; i < pattern.length; i++) {

            /**
             * 当pattern[j] != pattern[i]时，patternIndex 需要向前回溯，
             * 直到 pattern[j] == pattern[i] 或 patternIndex == 0
             */
            while (patternIndex > 0 && pattern[patternIndex] != pattern[i]) {
                // patternIndex 回溯到前一个公共前后缀值
                patternIndex = prefixTable[patternIndex - 1];
            }

            // 如果pattern[patternIndex] == pattern[i]，则公共前后缀值长度加1
            if (pattern[patternIndex] == pattern[i]) {
                patternIndex++;
            }
            prefixTable[i] = patternIndex;
        }

        return prefixTable;
    }
}
