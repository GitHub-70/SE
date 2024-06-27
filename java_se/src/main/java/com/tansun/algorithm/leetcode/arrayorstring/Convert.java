package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 6. Z 字形变换
 * <p>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 */
public class Convert {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convert(s, numRows));
        System.out.println(convert2(s, numRows));
    }

    /**
     * 压缩矩阵空间
     * 复杂度分析:
     * 时间复杂度：O(n)，其中n为字符串s的长度。
     * 空间复杂度：O(n)，其中n为字符串s的长度。
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        // 创建一个StringBuilder数组，数组长度为行数
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }
        /*
         * 思路：
         * 1、根据行数，创建一个StringBuilder数组，数组长度为行数
         * 2、遍历字符串，根据行数，将字符串放入对应的StringBuilder中
         * 3、将StringBuilder数组中的字符串拼接起来
         */
        // i：当前遍历到的字符串下标，flag：行数遍历方向，-1：向下，1：向上
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            sb[i].append(c);
            // 判断是否是第一行或者最后一行
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder : sb) {
            res.append(stringBuilder);
        }
        return res.toString();
    }

    /**
     * 该函数将给定的字符串按照Z字形排列后，按行读取并返回转换后的字符串。
     *      首先，将输入的字符串转换为字符数组。
     *      然后，根据给定的行数计算出填充的长度。
     *      如果行数为1，则直接返回原字符串。
     *      接下来，通过循环遍历字符数组，并按照Z字形排列的规律依次赋值。
     *      当n不等于1和行数时，还需要考虑字符数组长度不足的情况。
     *      最后，将字符数组转换为字符串并返回。
     * @param s
     * @param numRows
     * @return
     */
    public static String convert2(String s, int numRows) {
        char[] res = new char[s.length()];
        int index = 0;
        int curRows = 1; // 表示当前处理的是第几行
        // 计算Z字形排列中每次水平移动的最大距离padding，即两斜边间（不包括边缘）的字符数
        int padding = 2 * (numRows - 1); // if n(numRows) == 4, padding = 6;
        if (numRows == 1) return s;
        for (int i = 0; i < res.length; i++) {
            res[i] = s.charAt(index);
            // 当 curRows 不等于1和行数时，非首行和未行的填充长度 padding = 2 * (n - curRows)
            if (curRows != 1 && curRows != numRows) {
                // 计算填充长度
                int p = 2 * (numRows - curRows);
                // 如果字符数组长度不足，则跳过
                if (i + 1 < res.length && index + p < s.length())
                    res[++i] = s.charAt(index + p);
            }
            index = index + padding;
            if (index >= s.length()) {
                index = curRows;
                curRows++;
            }
        }

        return new String(res);
    }

}
