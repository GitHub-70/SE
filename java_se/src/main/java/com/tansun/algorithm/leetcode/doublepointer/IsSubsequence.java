package com.tansun.algorithm.leetcode.doublepointer;

public class IsSubsequence {

    public static void main(String[] args) {
//        String s = "abc";
//        String t = "ahbgdc";
//        String s = "abbc";
//        String t = "ahbdc";
        String s = "twn";
        String t = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxtxxxxxxxxxxxxxxxxxxxxwxxxxxxxxxxxxxxxxxxxxxxxxxn";
        System.out.println(isSubsequence(s, t));
        System.out.println(isSubsequence2(s, t));
    }

    /**
     * 双指针
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 时间复杂度：O(n+m) 其中 n 为 s 的长度，m 为 t 的长度
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        int i = 0;
        for (int j = 0; j < t.length(); j++) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            if (i == s.length()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 动态规划
     * 该函数用于判断字符串s是否为字符串t的子序列。函数使用动态规划方法，创建一个二维数组f，
     * 其中f[i][j]表示在字符串t的前i个字符中，字符j最后一次出现的位置加1。然后遍历字符串t，
     * 更新数组f。最后，遍历字符串s，通过查询数组f判断s中的字符是否按顺序出现在t中。
     * 如果s中的字符按顺序出现在t中，则返回true，否则返回false
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();

        // 创建一个大小为(m + 1) x 26的二维整型数组f
        int[][] f = new int[m + 1][26];
        // 初始化数组的最后一行（即索引为m的行），将所有元素设置为m。这意味着对于每个字母，
        // 如果它没有出现在t中，其位置默认设置为t的末尾之后，即行数m
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        /**
         * 构建失败函数
         * 从t的倒数第二个字符开始向前遍历至第一个字符（索引i从m - 1到0）。
         * 对于每个字符，遍历所有可能的字母（ASCII值从'a'到'z'，即j从0到25）。
         * 如果当前字符t.charAt(i)等于字母表中的第j + 1个字母（j + 'a'），则在f[i][j]记录当前位置i。
         * 否则，继承下一个位置的值，即f[i][j] = f[i + 1][j]。这表示如果当前字符不匹配，
         * 就沿用后一个字符对该字母的最远出现位置。
         */
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        /**
         * 检查子序列:
         * 初始化一个变量add为0，用于追踪t中的当前位置。
         * 遍历字符串s的每个字符。
         * 使用s.charAt(i) - 'a'获取当前字符在字母表中的索引。
         * 检查字符在t中的最远出现位置：f[add][s.charAt(i) - 'a']。
         * 如果这个位置等于t的长度m，说明当前s中的字符在t的已检查部分未找到，直接返回false。
         * 否则，更新add为找到的位置加1，继续检查s的下一个字符。
         */
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;

    }
}
