package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 68. 文本左右对齐
 *      给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，
 *      且左右两端对齐的文本。
 *      你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，
 *      使得每行恰好有 maxWidth 个字符。
 *      要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *      文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 注意:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *
 *
 * 示例 1:
 * 输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 *
 * 示例 2:
 * 输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 */
public class FullJustify {

    public static void main(String[] args) {
        String[] words = {"a"};
//        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
//        String[] words = {"What","must","be","acknowledgment","shall","be"};
//        String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        int maxWidth = 2;
        System.out.println(fullJustify(words, maxWidth));

    }

    /**
     * 贪心算法
     *  时间复杂度
     *      最好情况：如果每个单词的长度恰好构成一行或接近一行的最大宽度，几乎不需要内部循环来分配空格，
     *      此时的时间复杂度接近 O(n)，n 为单词数量。因为每个单词最多被访问两次（一次加入行中，一次加入结果列表）。
     *
     *      最坏情况：当单词长度差异大，导致某些行需要大量内部循环来分配空格时，每次处理行时的复杂度会增加。
     *      极端情况下，每次处理一行都需要对行内所有单词进行额外操作，这会导致总复杂度接近 O(n^2)，
     *      其中 n 是单词数量。但实际中这种情况较少，因为大部分情况下，空格的分配不会以每个单词都参与多次
     *      内部循环的方式进行。
     *
     *      综上所述，没有具体的单词长度分布信息，一般认为时间复杂度为 O(n*m)，其中 n 是单词数量，
     *      m 是单词平均需要参与的分配空格的“尝试”次数，这个 m 在实际情况中通常远小于 n，
     *      因此实际运行效率可能更接近线性。
     *  空间复杂度
     *      输入存储：存储输入单词数组 words 需要 O(n) 空间，n 是单词数量。
     *      结果存储：存储最终排版结果的 result 列表也会占用空间，最坏情况下（每个单词单独成行），需要 O(n) 空间。
     *      辅助变量：包括 rowWord（StringBuilder），blankNum，wordsWidth 等辅助变量，
     *      它们的空间消耗相对于单词数量来说是常数级别的。
     *      综合考虑，主要的空间消耗来自于输入和输出，因此整体空间复杂度为 O(n)。
     * @param words
     * @param maxWidth
     * @return
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if (words.length == 1) {
            if (words[0].length() < maxWidth) {
                StringBuilder stringBuilder = new StringBuilder(words[0]);
                for (int i = 0; i < maxWidth - words[0].length(); i++) {
                    stringBuilder.append(" ");
                }
                result.add(stringBuilder.toString());
            } else {
                result.add(words[0]);
            }
            return result;
        }
        StringBuilder rowWord = new StringBuilder();
        int blankNum = 0;
        int wordsWidth = 0;
        for (int i = 0; i < words.length; i++) {
            wordsWidth += words[i].length();
            // 大于当前行宽度
            if (wordsWidth + blankNum > maxWidth && blankNum > 0) {
                // 计算填充空格数量
                blankNum--;
                int needblankNum = maxWidth - wordsWidth + words[i].length();
                if (blankNum == 0){
                    // 取前一个单词
                    rowWord.append(words[i - blankNum - 1]);
                    for (int j = 0; j < needblankNum; j++) {
                        rowWord.append(" ");
                    }
                } else {
                    int argBlankNum = needblankNum / blankNum;  // 均值
                    int blankNum3 = needblankNum % blankNum;  // 余数
                    while (blankNum + 1 > 0) {
                        rowWord.append(words[i - blankNum - 1]);
                        if (blankNum == 0) {
                            break;
                        }
                        // 均值空格
                        for (int j = 0; j < argBlankNum; j++) {
                            rowWord.append(" ");
                        }
                        // 是否还有余数
                        if (blankNum3 > 0) {
                            rowWord.append(" ");
                            blankNum3--;
                        }
                        blankNum--;
                    }
                }
                // 重回前一个位置
                i--;
                wordsWidth = 0;
                result.add(rowWord.toString());
                rowWord.delete(0, rowWord.length());
            } else if (wordsWidth + blankNum == maxWidth) {
                // 没有空格，说明一个单词填满
                if (blankNum == 0) {
                    rowWord.append(words[i - blankNum]);
                } else {
                    while (blankNum >= 0) {
                        rowWord.append(words[i - blankNum]);
                        if (blankNum == 0) {
                            break;
                        }
                        rowWord.append(" ");
                        blankNum--;
                    }
                }
                wordsWidth = 0;
                result.add(rowWord.toString());
                rowWord.delete(0, rowWord.length());
            } else {
                // 如果是最后一个元素，左对齐
                if (i == words.length - 1) {
                    // 计算最后需填充的空格
                    int lastBlankNum = maxWidth - wordsWidth - blankNum;
                    int lasetWordsNum = blankNum + 1;
                    for (int z = 0; z < lasetWordsNum; z++) {
                        rowWord.append(words[i - blankNum]);
                        if (blankNum == 0) {
                            break;
                        }
                        rowWord.append(" ");
                        blankNum --;
                    }
                    for (int j = 0; j < lastBlankNum; j++) {
                        rowWord.append(" ");
                    }
                    result.add(rowWord.toString());
                }
                blankNum++;
            }
        }
        return result;
    }

    /**
     * 根据题干描述的贪心算法，对于每一行，我们首先确定最多可以放置多少单词，这样可以得到该行的空格个数，
     * 从而确定该行单词之间的空格个数。
     *
     * 根据题目中填充空格的细节，我们分以下三种情况讨论：
     *      当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格；
     *      当前行不是最后一行，且只有一个单词：该单词左对齐，在行末填充空格；
     *      当前行不是最后一行，且不只一个单词：设当前行单词数为 numWords，空格数为 numSpaces，
     *      我们需要将空格均匀分配在单词之间，则单词之间应至少有 numSpaces/(numWords-1) 个空格，
     *
     */
    public static List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<String>();
        int right = 0, n = words.length;
        while (true) {
            int left = right; // 当前行的第一个单词在 words 的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 拼接额外加一个空格的单词
            sb.append(blank(avgSpaces));
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(sb.toString());
        }
    }

    // blank 返回长度为 n 的由空格组成的字符串
    private static String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
    private static StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }

}
