package com.tansun.algorithm.kmp;

/**
 * KMP算法：
 * KMP 是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置 的经典算法
 * Knuth-Morris-Pratt 字符串查找算法，简称为 “KMP 算法”，
 * <p>
 * KMP算法的大致步骤
 * 1.求出模式字符串patternStr的部分匹配表，已知待匹配的字符串 matchStr
 * 2.定义两个指针 i 和 j，分别指向 patternStr 和 matchStr ，初始化为0
 * 3.判断 patternStr[i] 和 matchStr[j] 是否相等
 * 如果相等，则继续向后匹配：i++, j++
 * 如果不相等，则 i 不变，调整 j 为 模式字符串pattern  的 上一个子串（索引 [ 0, j-1 ]）的最长公共前缀字符串的
 * 下一个索引位置，该索引位置也是最长公共前缀/后缀字符串的长度：j = next[ j - 1 ]
 * 4.判断 i 和 j 是否超出 各自的最大索引值
 * 如果都没到超出 各自最大索引值，就继续执行第三步进行比较（说明是个循环）
 * 如果至少有一个超出了 各自最大索引值，就退出循环
 * 5.循环结束后，判断 j 是否超出了 模式字符串的最大索引值
 * 如果超出了，说明匹配成功，返回 patternStr 字符串匹配到的 matchStr 的第一个字符串的起始索引位置：i - j
 * 如果没有超出，说明匹配失败，返回 -1 表示没有匹配到
 * <p>
 * https://blog.csdn.net/qq_62982856/article/details/128003067
 */
public class KMPAlgorithm {

    public static void main(String[] args) {
        String matchStr = "AABABADDABAC";
//        String patternStr = "ABA";
        String patternStr = "BABAD";

        // 输出：index = 1
        System.out.println("index = " + kmpSearch(matchStr, patternStr, kmpNext(patternStr)));

    }

    /**
     * kmp搜索算法
     *      todo  匹配成功一个就退出匹配的代码
     * @param matchStr   原字符串
     * @param patternStr 子串
     * @param next       子串对应的部分匹配表
     * @return 如果是-1，就是没有匹配到，否则就返回第一个匹配的位置
     */
    public static int kmpSearch(String matchStr, String patternStr, int[] next) {

        int i = 0, j = 0;

        while (i < matchStr.length() && j < patternStr.length()) {

            if (matchStr.charAt(i) == patternStr.charAt(j)) {
                //相等就继续进行匹配
                i++;
                j++;

            } else {
                //如果 patternStr[i] 和 matchStr[j] 不相等

                if (j == 0) {
                    /*
                        表示 matchStr 没有匹配到 patternStr的第一个字符
                        那直接将 matchStr 的指针 i 向后移动一位即可
                     */
                    i++;
                } else {
                    j = next[j - 1];
                }
            }

        }
        return j == patternStr.length() ? i - j : -1;
    }

    /**
     * 获取一个字符串 pattern 的部分匹配表
     *
     * @param patternStr 用于模式匹配字符串
     * @return 存储部分匹配表的每个子串的最长公共前后缀的 next数组
     *
     * next数组:
     *      下标  	 0  1  2  3  4  5  6
     *      模式串    a  b  c  a  b  e  a
     *      next[i]  0  0  0  0  1  2  0
     *
     * 最长公共前后缀长度：
     * 首先，要了解两个概念："前缀"和"后缀"。
     * "前缀"指除了最后一个字符以外，一个字符串的全部头部组合；
     * "后缀"指除了第一个字符以外，一个字符串的全部尾部组合。
     *
     * 下面再以”ABCDABD”为例，进行介绍：
     * －　”A”的前缀和后缀都为空集，共有元素的长度为0；
     * －　”AB”的前缀为[A]，后缀为[B]，共有元素的长度为0；
     * －　”ABC”的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；
     * －　”ABCD”的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；
     * －　”ABCDA”的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为”A”，长度为1；
     * －　”ABCDAB”的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为”AB”，长度为2；
     * －　”ABCDABD”的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD, ABD, BD, D]，共有元素的长度为0。
     *
     * 部分匹配表的作用
     *      在部分匹配表中，每个位置记录了模式串中该位置之前的子串的最长前缀和最长后缀相同的长度。
     *      这个长度告诉我们，在模式串中当前位置的字符不匹配时，可以向右移动多少位来继续匹配，
     *      而不需要重新比较已经匹配的部分
     *
     */
    public static int[] kmpNext(String patternStr) {
        //将 patternStr 转为 字符数组形式
        char[] patternArr = patternStr.toCharArray();

        //预先创建一个next数组，用于存储部分匹配表的每个子串的最长公共前后缀
        int[] next = new int[patternStr.length()];

        /*
            从第一个字符（对应索引为0）开始的子串，如果子串的长度为1，那么肯定最长公共前后缀为0
            因为这唯一的一个字符既是第一个字符，又是最后一个字符，所以前后缀都不存在 -> 最长公共前后缀为0
         */
        next[0] = 0;

        /*
          len 有两个作用：
            1. 用于记录当前子串的最长公共前后缀长度
            2. 同时知道当前子串的最长公共前后缀的前缀字符串对应索引 [0,len-1]  <-- 可以拿示例分析一下
         */
        int len = 0;

        //从第二个字符开始遍历，求索引在 [0,i] 的子串的最长公共前后缀长度
        int i = 1;
        while (i < patternArr.length) {
            /*
                1.已经知道了上一个子串 对应索引[0,i-1] 的最长公共前后缀长度为 len
                  的前缀字符串是 索引[0,len-1]，对应相等的后缀字符串是 索引[i-len,i-1]

                2.因此我们可以以 上一个子串的最长公共前后缀字符串 作为拼接参考
                  比较一下 patternArr[len] 与 patternArr[i] 是否相等
             */
            if (patternArr[len] == patternArr[i]) {
                /*
                    1.如果相等即 patternArr[len]==patternArr[i]，
                      那么就可以确定当前子串的最长公共前后缀的
                      前缀字符串是 索引[0,len] ，对应相等的后缀字符串是 索引[i-len,i]

                    2.由于是拼接操作，那么当前子串的最长公共前后缀长度只需要在上一个子串的最长公共前后缀长度的基础上 +1 即可
                      即 next[i] = next[i-1] + 1 ，

                    3.由于 len 是记录的子串的最长公共前后缀长度，对于当前我们所在的代码位置而言
                      len 还是记录的上一个子串的最长公共前后缀长度，因此：
                      next[i] = next[i-1] + 1 等价于 next[i] = ++len
                 */

                // 等价于 next[i] = next[i-1] + 1
                next[i] = ++len;
                //既然找到了索引在[0,i]的子串的最长公共前后缀字符串长度，那就 i+1 去判断以下一个字符结尾的子串的最长公共前后缀长度
                i++;
            } else {
                /*
                    1.如果不相等 patternArr[len]!=patternArr[i]
                      我们想要求当前子串 对应索引[0,i] 的最长公共前后缀长度
                      我们就不能以 上一个子串的最长公共前后缀：前缀字符串pre  后缀字符串post （毫无疑问pre==post） 作为拼接参考

                    2.但可以思考一下：
                      pre的最长公共前缀字符串： 索引 [      0        , next[j-1] )
                      是等于
                      post的最长公共后缀字符串：索引 [ i-next[len-1] ,     i       )

                      则我们 就以 pre的最长公共前缀字符串/post的最长公共后缀字符串 作为拼接参考
                      去判断 pre的最长公共前缀字符串的下一个字符patternArr[next[len-1]] 是否等于 post的最长公共后缀字符串的下一个字符patternArr[i]

                    3.在第 1,2 步分析的基础上
                      我们可以在判断出 patternArr[len]!=patternArr[i] 后，
                      不去执行第二步：patternArr[next[len-1]] 是否等于 patternArr[i]，
                      可以先修改len的值：len = next[len-1]，len就成了 pre的最长公共前缀字符串长度/post的最长公共后缀字符串长度，
                      修改完之后，再去判断下一个字符 是否相等，即 判断 patternArr[len] 是否等于 patternArr[i]
                      仔细观察，这不又是在判断 这个循环中 if-else 语句吗

                    4.关于 len 这个值，在循环开始时我们解释的是：上一个子串的最长公共前后缀字符串的长度
                      但实际上我们在这里改为 len = next[len-1] 表示上一个子串的最长公共前后缀字符串的最长公共前后缀字符串的长度
                      是没有问题的，等价于上一个子串的较小的公共前后缀字符串。
                      既然进入了 else 语句说明字符不相等，就不能以 上一个子串的最长公共前后缀字符串 作为 拼接参考，就应当去缩小参考范围。
                 */
                if (len == 0) {

                    /*
                        len为0说明上一个子串已经没有了公共前后缀字符串
                        则我们没有继续寻找的必要 --> 索引在[0, i]的当前子串的最长公共前后缀字符串长度就是0
                     */
                    next[i] = len;

                    //继续寻找下一个字符串的最长公共前后缀字符串长度
                    i++;

                } else {
                    len = next[len - 1];
                }

            }
        }
        return next;
    }

}
