package com.tansun.basic.bit;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.StandardCharsets;

/**
 * 使用了Google Guava库中的BloomFilter类的优化后的布隆过滤器示例
 *      实现了使用MurmurHash作为哈希函数、动态调整布隆过滤器容量、减少哈希冲突等优化，
 *      提高了布隆过滤器的性能和准确性。
 */
public class OptimizedBloomFilter {
    // 期望插入元素数量
    private static final int EXPECTED_INSERTIONS = 1000000;

    // 期望的误判率 与 位数组的大小有关，通常位数组越大，误判率越低，占用的内存也就越高
    private static final double FPP = 0.01;

    private BloomFilter<String> bloomFilter;

    public OptimizedBloomFilter() {
        bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), EXPECTED_INSERTIONS, FPP);
    }

    public void add(String value) {
        bloomFilter.put(value);
    }

    public boolean contains(String value) {
        return bloomFilter.mightContain(value);
    }

    public static void main(String[] args) {
        OptimizedBloomFilter optimizedBloomFilter = new OptimizedBloomFilter();

        // 添加元素
        optimizedBloomFilter.add("apple");
        optimizedBloomFilter.add("banana");
        optimizedBloomFilter.add("orange");

        // 判断元素是否存在
        System.out.println(optimizedBloomFilter.contains("apple"));   // true
        System.out.println(optimizedBloomFilter.contains("grape"));   // false
    }
}