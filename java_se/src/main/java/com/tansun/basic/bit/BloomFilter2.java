package com.tansun.basic.bit;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

public class BloomFilter2 {

    /**
     * 哈希函数的数量：哈希函数的数量指的是在某个哈希算法中实际使用的哈希函数的个数。
     * 通常情况下，一个哈希算法会包含多个哈希函数，用于对输入数据进行多次哈希计算，
     * 以增加哈希的随机性和抗碰撞性。
     *
     * 哈希函数的种子：哈希函数的种子是在哈希计算过程中引入的一个随机值，
     * 用于初始化哈希函数的状态或影响哈希计算的结果。种子可以影响哈希函数的输出，
     * 从而在相同输入的情况下产生不同的哈希值。
     */
    private final int[] seeds = {1, 3, 5}; // 多个哈希函数的种子
    private final int bitSetSize; // 位数组大小
    private final BitSet bitSet;
    private final int expectedElementCount;
    private final int hashFunctionCount;

    /**
     * 初始化
     * @param expectedElementCount      期望元素个数
     * @param falsePositiveProbability  误判率
     */
    public BloomFilter2(int expectedElementCount, double falsePositiveProbability) {
        this.expectedElementCount = expectedElementCount;
        this.hashFunctionCount = calculateHashFunctionCount(falsePositiveProbability);
        this.bitSetSize = calculateBitSetSize(expectedElementCount, falsePositiveProbability);
        this.bitSet = new BitSet(bitSetSize);
    }

    public void add(Object key) {
        for (int seed : seeds) {
            int hash = MurmurHash3.hash(key, seed);
            int index = Math.abs(hash) % bitSetSize;
            bitSet.set(index, true);
        }
    }

    public boolean contains(Object key) {
        for (int seed : seeds) {
            int hash = MurmurHash3.hash(key, seed);
            int index = Math.abs(hash) % bitSetSize;
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 通过误判率 fpp 来计算 哈希函数的数量
     *      在Bloom Filter中，一个元素会被多个哈希函数映射到不同的位上，
     *      因此需要使用多个哈希函数来生成多个哈希值。
     * @param falsePositiveProbability
     * @return int
     *
     *  根据期望的误判率falsePositiveProbability，
     *  通过公式-log2(falsePositiveProbability)来计算哈希函数的数量
     */
    private int calculateHashFunctionCount(double falsePositiveProbability) {
        return (int) Math.ceil(-Math.log(falsePositiveProbability) / Math.log(2));
    }

    /**
     * 通过 期望样本的元素个数，与误判率 来动态计算位数组大小
     * @param expectedElementCount
     * @param falsePositiveProbability
     * @return int
     */
    private int calculateBitSetSize(int expectedElementCount, double falsePositiveProbability) {
        int size = (int) Math.ceil((expectedElementCount * Math.log(falsePositiveProbability)) / Math.log(1 / Math.pow(2, Math.log(2))));
        return size;
    }

    // MurmurHash3算法
    static class MurmurHash3 {
        public static int hash(Object key, int seed) {
            // 实现MurmurHash3算法，生成哈希值
            // 省略实现细节
            Hashing.murmur3_32().hashString((String)key, StandardCharsets.UTF_8).asInt();
            return 0;
        }
    }
}
