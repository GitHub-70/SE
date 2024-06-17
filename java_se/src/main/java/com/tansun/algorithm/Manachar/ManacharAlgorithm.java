package com.tansun.algorithm.Manachar;

/**
 * Manachar算法（马拉车）
 *      回文字符串的通俗定义是：如果一个字符串正着读或反着读都一样，那么称这个字符串为回文字符串
 *      回文半径：回文子串最中间的那个字符到回文子串最左边的长度叫回文半径
 *      回文串的基本解法
 *        以每一个点为中心对称点，每次保留最长回文子串的长度，最后得到的就是最长回文子串的长度，
 *        但是这样的时间复杂度为O（n^2），并且奇偶回文串是不一样的，比如“aba”的对称中心为“b”，
 *        而“abba”的对称中心在两个“b”之间，所以，为了解决这样的问题，可以在每两个字符之间添加一个特殊字符，
 *        比如“#”，字符串变成这样“#a#b#b#a#”，求得中间“#”的回文串长度为9，再除2，就得到回文串的长度为4。
 *
 *        基础解法的时间复杂度太高了，所以就有了Manacher算法，他的复杂度为O(n)，用起来很方便。
 */
public class ManacharAlgorithm {

    public static void main(String[] args) {
        String msg = "aa12345678987654321bb";
        String longestPalindrome = getLongestPalindrome(msg);
        System.out.println(longestPalindrome);
    }

    // 返回最长回文串长度。
    public static String getLongestPalindrome(String s) {
        int charLen = s.length();// 源字符串的长度。
        int length = charLen * 2 + 1;// 添加特殊字符之后的长度。
        char[] chars = s.toCharArray();// 源字符串的字符数组。
        char[] res = new char[length];// 添加特殊字符的字符数组。
        int index = 0;
        for (int i = 0; i < res.length; i++)  // 添加特殊字符。
            res[i] = (i % 2) == 0 ? '#' : chars[index++];

        // 新建p数组 ，p[i]表示以res[i]为中心的回文串半径。
        int[] p = new int[length];
        // right(某个回文串延伸到的最右边下标)，
        // maxCenter(right所属回文串中心下标)，
        // resCenter（记录遍历过的最大回文串中心下标，主要用于最后字符串截取），
        // resLen（记录遍历过的最大回文半径，主要用于最后字符串截取），
        int right = 0, maxCenter = 0, resCenter = 0, resLen = 0;
        for (int i = 0; i < length; i++) {
            // 合并后的代码
            p[i] = right > i ? Math.min(right - i + 1, p[2 * maxCenter - i]) : 1;
            // 上面的代码只能确定 [i,right-1] 的回文情况，至于 right 以及 right 之后的
            // 部分，就只能一个个去匹配了，匹配的时候首先数组不能越界。
            while (i - p[i] >= 0 && i + p[i] < length && res[i - p[i]] == res[i + p[i]])
                p[i]++;
            // 记录更远的 right 以及 maxCenter。
            if (i + p[i] > right) {
                right = i + p[i] - 1;
                maxCenter = i;
            }
            // 记录最长回文串的半径和中心位置，这个主要用于后面截取然后返回。
            if (p[i] > resLen) {
                resLen = p[i];
                resCenter = i;
            }
        }
        // 计算最长回文串的长度和开始的位置。
        resLen = resLen - 1;
        int start = (resCenter - resLen) >> 1;
        // 截取最长回文子串。
        return s.substring(start, start + resLen);
    }

}
