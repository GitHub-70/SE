package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。
 * 12 写做 XII ，即为 X + II 。
 * 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 */
public class RomanToInt {

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }


    /**
     * 罗马数字转整数
     *      按照题目的描述，可以总结如下规则：
     *  1.罗马数字由 I,V,X,L,C,D,M 构成；
     *  2.当小值在大值的左边，则减小值，如 IV=5-1=4；
     *  3.当小值在大值的右边，则加小值，如 VI=5+1=6；
     *  4.由上可知，右值永远为正，因此最后一位必然为正。
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;

    }
    private static int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }


    public static int romanToInt2(String s) {
        int result = 0;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    i++;
                    if (i < s.length()) {
                        char cc = s.charAt(i);
                        if (cc == 'V') {
                            result += 4;
                        } else if (cc == 'X') {
                            result += 9;
                        } else if (cc == 'I') {
                            result += 2;
                        } else {
                            i--;
                            result += 1;
                        }
                    } else {
                        i--;
                        result += 1;
                    }

                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    i++;
                    if (i < s.length()) {
                        char cc = s.charAt(i);
                        if (cc == 'L') {
                            result += 40;
                        } else if (cc == 'C') {
                            result += 90;
                        } else if (cc == 'X') {
                            result += 20;
                        } else {
                            i--;
                            result += 10;
                        }
                    } else {
                        i--;
                        result += 10;
                    }

                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    i++;
                    if (i < s.length()) {
                        char cc = s.charAt(i);
                        if (cc == 'D') {
                            result += 400;
                        } else if (cc == 'M') {
                            result += 900;
                        } else if (cc == 'C') {
                            result += 200;
                        } else {
                            i--;
                            result += 100;
                        }
                    } else {
                        i--;
                        result += 100;
                    }
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
                default:
                    break;
            }
            i++;
        }
        return result;
    }
}
