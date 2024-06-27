package com.tansun.basic.bit;

import java.util.BitSet;

/**
 * BitSet是Java中用于存储位值的数据结构，它可以有效地表示一组位（bit），并且支持对位的操作，
 * 如设置、清除、翻转和查询等。BitSet主要用于表示一组二进制位的状态，通常用于布尔过滤器、位图等场景。
 *
 * BitSet的常用方法包括：
 *
 * set(int index)：将指定位置的位设置为1。
 * clear(int index)：将指定位置的位清除为0。
 * flip(int index)：将指定位置的位翻转（0变为1，1变为0）。
 * get(int index)：获取指定位置的位值。
 * length()：返回BitSet的位数。
 * and(BitSet set)：对当前BitSet和另一个BitSet进行与操作。
 * or(BitSet set)：对当前BitSet和另一个BitSet进行或操作。
 * xor(BitSet set)：对当前BitSet和另一个BitSet进行异或操作。
 */
public class BitSetExample {
    public static void main(String[] args) {
        // 1字节(b-byte)=8bit,utf-8编码时，一个字符=3个字节
        BitSet bitSet = new BitSet(8); // 创建一个包含8位的BitSet

        // 设置第2位和第5位为1
        bitSet.set(2);
        bitSet.set(5);

        // 输出BitSet的内容
        System.out.println("BitSet: " + bitSet);

        // 清除第5位
        bitSet.clear(5);

        // 输出BitSet的内容
        System.out.println("BitSet after clear: " + bitSet);

        // 翻转第2位
        bitSet.flip(2);

        // 输出BitSet的内容
        System.out.println("BitSet after flip: " + bitSet);

        // 获取第2位和第5位的值
        System.out.println("Bit 2: " + bitSet.get(2));
        System.out.println("Bit 5: " + bitSet.get(5));
    }
}
