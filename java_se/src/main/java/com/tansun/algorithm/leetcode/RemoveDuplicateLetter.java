package com.tansun.algorithm.leetcode;

import java.util.*;

/**
 * 去除重复字母
 *
 * ASCII（American Standard Code for Information Interchange，美国信息交换标准代码）
 * 是一种基于拉丁字母的字符编码，它使用7位二进制数来表示128个不同的字符，
 * 包括大写和小写字母、数字、标点符号以及一些控制字符。ASCII编码通常占用一个字节，最高位（第八位）为0。
 *
 * Unicode是一个字符集，它旨在包含世界上几乎所有的书写系统，包括字母、符号和表情等。
 * Unicode组织维护着Unicode标准，目前最新的版本包含超过140,000个代码点。
 * Unicode使用多种编码形式来表示这些字符，如UTF-8、UTF-16和UTF-32等。
 * UTF-8是目前最常用的Unicode编码，它可以表示整个Unicode字符集，
 * 并且对ASCII字符友好：ASCII字符在UTF-8编码下仍然只用一个字节表示。
 * 所以，ASCII是Unicode的一个子集，只包含了最基本的128个字符，
 * 而Unicode则是一个更广泛的标准，包含了ASCII和其他许多语言和符号的字符。
 * 在处理文本时，使用Unicode编码可以确保跨语言和跨平台的兼容性。
 *
 *
 */
public class RemoveDuplicateLetter {

    public static void main(String[] args) {
        String s = "sdhlngdlsdaaa3454ADA";
        String duplicateLetters = removeDuplicateLetters(s);
        String duplicateLetters2 = removeDuplicateLetters2(s);
        System.out.println(duplicateLetters);
        System.out.println(duplicateLetters2);
    }

    /**
     * 从给定字符串中移除重复的字符，并返回移除后的字符串。函数使用了栈数据结构和计数器数组来实现。
     *      1.创建一个队列类型的栈stack和一个整型数组count，用于存储每个字符出现的次数。
     *      2.遍历字符串s，统计每个字符出现的次数，并存储在count数组中。
     *      3.创建一个布尔类型数组add，用于记录每个字符是否已经添加到栈中。
     *      4.再次遍历字符串s，对于每个字符c，将其出现次数减1。
     *      5.如果字符c已经添加到栈中，则跳过。
     *      6.如果字符c没有添加到栈中，且栈顶元素大于c，并且栈顶元素后面还有相同元素，则将栈顶元素出栈。
     *      7.将字符c添加到栈中，并将add[c]标记为true。
     *      8.将栈中的字符按顺序取出，拼接到StringBuilder对象stringBuilder中，并返回stringBuilder转换后的字符串。
     *
     *      该函数的时间复杂度为O(n)，其中n为字符串s的长度。
     * @param s
     * @return
     */
    public static String removeDuplicateLetters(String s){
        Deque<Character> stack = new LinkedList<>();
        /**
         * 仅覆盖了ASCII字符集的范围（从0到127），这确实限制了对扩展ASCII字符或Unicode字符的处理能力。
         * 如果需要处理包括大写字母、特殊符号乃至更广泛的Unicode字符，
         * 应该将计数数组的长度调整为Character.MAX_VALUE + 1，以确保能够覆盖所有可能的char值。
         */
        int [] count = new int[128]; // 用于统计每个字符的数量
//        int[] count = new int[Character.MAX_VALUE + 1];
        for (int i = 0; i < s.length(); i++) {
            // 在Java中，字符数据类型实际上对应了ASCII编码值
            count[s.charAt(i)]++; // 统计每个字符的数量
        }
        // 记录对应的字符有没有添加到栈中
        boolean[] add = new boolean[128];
        for (char c : s.toCharArray()) {
            count[c]--;// 遍历到当前字符，数量要减1
            // 如果当前字符已经添加到栈中，就跳过
            if (add[c])
                continue;

            // 如果当前字符没有添加到栈中，栈顶字符比当前字符大
            // 并且栈顶字符后面还有，就让栈顶字符出栈  TODO 未能保证字符串的循序
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek()] > 0){
                add[stack.pop()] = false;
            }
            // 把当前字符添加到栈中,并且当前字符串的位置为true
            stack.push(c);
            add[c] = true;
        }

        // 这里把栈中的字符转化为字符串
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()){
            stringBuilder.append(stack.pollLast());
        }
        return stringBuilder.toString();
    }

    /**
     * 优化版
     * 移除重复字符，返回移除后的字符串。该函数的时间复杂度为O(n)，其中n为字符串s的长度。
     * @param s
     * @return
     */
    public static String removeDuplicateLetters2(String s) {
        // 边界条件处理
        if (s == null || s.isEmpty()) {
            return "";
        }

        // 使用HashSet来记录字符是否已经添加，使用TreeSet来维护一个有序的字符集合
        TreeSet<Character> treeSet = new TreeSet<>();
        HashSet<Character> addedChars = new HashSet<>();

        for (char c : s.toCharArray()) {
            // 如果字符已经在TreeSet中，跳过
            if (treeSet.contains(c)) {
                continue;
            }

            // 从TreeSet中移除所有大于当前字符且还未被消耗掉的字符
            while (!treeSet.isEmpty() && treeSet.last() > c && !addedChars.contains(treeSet.last())) {
                // 移除集合中最大的元素
                treeSet.remove(treeSet.last());
            }

            treeSet.add(c);
            addedChars.add(c);
        }

        // 将TreeSet中的字符转换为字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : treeSet) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

}
