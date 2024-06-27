package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.*;

/**
 * 反转字符串中的单词
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，
 * 单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * <p>
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * <p>
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 * <p>
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 */
public class ReverseWords {

    public static void main(String[] args) {
        String s = "a good   example";
        System.out.println(reverseWords(s));
        System.out.println(reverseWords2(s));
        System.out.println(reverseWords3(s));
        System.out.println(reverseWords4(s));
    }

    /**
     * 目前最优解法
     * 时间复杂度：O(n)，其中 n 为输入字符串的长度。
     * 空间复杂度：O(m)，其中m为结果字符串的长度。主要取决于输入字符串中单词的数量及单词间的空格
     * @param s
     * @return
     */
    public static String reverseWords5(String s) {
        char[] sArr = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        // 倒着遍历
        for(int i=sArr.length-1;i>=0;i--){
            // 找到单词的结束位置
            while(i>=0 && sArr[i]==' '){
                --i;
            }
            int end = i;
            if(i<0){
                break;
            }
            // 找到单词的起点
            while(i>=0 && sArr[i]!=' '){
                --i;
            }
            ans.append(" ").append(s.substring(i+1,end+1));
        }
        // 去掉开头和末尾的空格
        return ans.toString().trim();
    }

    public static String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--) {
            if (!"".equals(strings[i])) {
                if (stringBuilder.length() == 0) {
                    stringBuilder.append(strings[i]);
                } else {
                    stringBuilder.append(" ");
                    stringBuilder.append(strings[i]);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String reverseWords2(String s) {
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        // 记录每个单词,通过倒序拼接
        StringBuilder partByte = new StringBuilder();
        int startByte = 0;
        for (int i = chars.length-1; i >= 0; i--) {
            // 空格 ASCII码表 值为32
            if (chars[i] != 32){
                if (partByte.length() == 0){
                    partByte.append(chars[i]);
                    startByte = 1;
                } else if (startByte == 1){
                    partByte.append(chars[i]);
                } else {
                    partByte.append(' ');
                    partByte.append(chars[i]);
                    startByte = 1;
                }

            } else if (chars[i] == 32 && stringBuilder.length()==0){
                stringBuilder.append(partByte.reverse());
                partByte.delete(0,partByte.length());
                startByte = 0;
            } else if (chars[i] == 32 && startByte == 1){
                stringBuilder.append(' ');
                stringBuilder.append(partByte.reverse());
                partByte.delete(0,partByte.length());
                startByte = 0;
            }

        }
        // 最后一个单词后面没有空格，需拼接最后一个单词
       if (partByte.length()>0 && stringBuilder.length() > 0){
           stringBuilder.append(' ');
           stringBuilder.append(partByte.reverse());
       } else {
           // 翻转单词拼接
           stringBuilder.append(partByte.reverse());
       }
        return stringBuilder.toString();
    }

    /**
     * 双端队列
     * 时间复杂度为O(n)
     * 似乎性能不如上面两个
     * @param s
     * @return
     */
    public static String reverseWords3(String s) {
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        // 记录每个单词,通过倒序拼接
        Deque partByte = new LinkedList();
        for (int i = chars.length-1; i >= 0; i--) {
            // 空格 ASCII码表 值为32
            if (chars[i] != 32){
                partByte.addFirst(chars[i]);

            } else if (chars[i] == 32 && stringBuilder.length()==0){
                while (partByte.size()>0){
                    stringBuilder.append(partByte.pollFirst());
                }
            } else if (chars[i] == 32 && partByte.size() >0){
                stringBuilder.append(' ');
                while (partByte.size()>0){
                    stringBuilder.append(partByte.pollFirst());
                }
            }

        }
        // 最后一个单词后面没有空格，需拼接最后一个单词
        if (partByte.size()>0 && stringBuilder.length() > 0){
            stringBuilder.append(' ');
            while (partByte.size()>0){
                stringBuilder.append(partByte.pollFirst());
            }
        } else {
            // 翻转单词拼接
            while (partByte.size()>0){
                stringBuilder.append(partByte.pollFirst());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 时间复杂度：O(n)，其中 n 为输入字符串的长度。
     *
     * 空间复杂度：O(n)，用来存储字符串分割之后的结果。
     * @param s
     * @return
     */
    public static String reverseWords4(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }


}
