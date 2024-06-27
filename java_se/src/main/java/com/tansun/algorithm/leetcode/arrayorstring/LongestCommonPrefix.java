package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
//        String[] strs = {"dog","racecar","car"};
//        String[] strs = {"","b"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefix2(strs));

    }

    /**
     * 横向比较
     * 时间复杂度 O(n*m)
     * 空间复杂度 O(1)
     * @param strs
     * @return
     */
    private static String longestCommonPrefix(String[] strs) {
        int minLength = -1;
        int i = 0;
        while (i < strs.length) {
            minLength = minLength == -1 ? strs[i].length() : Math.min(minLength, strs[i].length());
            i++;
        }
        String str = strs[0];
        char[] chars = str.toCharArray();
        int commonIndex = 0;
        for (int len = 0, j = 0; len < minLength; len++) {
            while (j < strs.length) {
                if (chars[len] == strs[j].charAt(len)) {
                    if (j == strs.length - 1) {
                        commonIndex += 1;
                        j = 0;
                        break;
                    }
                    j++;
                } else {
                    len = minLength;
                    break;
                }

            }
        }
        StringBuilder commonStr = new StringBuilder("");
        for (int h = 0; h < commonIndex; h++) {
            commonStr.append(chars[h]);
        }
        return commonStr.toString();
    }

    /**
     * 暴力法
     * 时间复杂度：可以表示为 O(N*M)，其中 N 是字符串数组的长度，M 是字符串的平均长度。
     * 空间复杂度：O(1)
     * @param strs
     * @return
     */
    private static String longestCommonPrefix2(String[] strs){
        if (strs.length == 0) return "";
        //公共前缀比所有字符串都短，随便选一个先
        String s = strs[0];
        for (String string : strs) {
            while (!string.startsWith(s)) {
                if (s.length() == 0) return "";
                //公共前缀不匹配就让它变短！
                s = s.substring(0, s.length() - 1);
            }
        }
        return s;
    }

}
