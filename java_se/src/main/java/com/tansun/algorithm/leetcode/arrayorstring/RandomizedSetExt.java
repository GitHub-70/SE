package com.tansun.algorithm.leetcode.arrayorstring;

import java.util.*;

/**
 * 实现RandomizedSet 类：
 * <p>
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * <p>
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 */
public class RandomizedSetExt extends RandomizedSet {

    public static void main(String[] args) {
        RandomizedSetExt randomizedSet = new RandomizedSetExt();
        System.out.println(randomizedSet.insert(3));
        System.out.println(randomizedSet.remove(4));
        System.out.println(randomizedSet.insert(4));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.remove(3));
        System.out.println(randomizedSet.insert(4));
        System.out.println(randomizedSet.getRandom());
    }

}

class RandomizedSet {
    List<Integer> nums;
    Map<Integer, Integer> indices;
    Random random;

    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        indices = new HashMap<Integer, Integer>();
        random = new Random();
    }

    /**
     * 插入
     *
     * @param val
     * @return
     */
    public boolean insert(int val) {
        // O(1)
        if (indices.containsKey(val)) {
            return false;
        }
        int index = nums.size();
        nums.add(val);
        indices.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if (!indices.containsKey(val)) {
            return false;
        }
        int index = indices.get(val);
        int last = nums.get(nums.size() - 1);
        // 用于在给定索引位置上设置新元素，并返回该位置上原有的元素，即将 index 元素设置为 last
        nums.set(index, last);
        // 跟新 map 中 last 元素的索引
        indices.put(last, index);
        // 删除操作的重点在于将变长数组的最后一个元素移动到待删除元素的下标处，然后删除变长数组的最后一个元素。
        // 该操作的时间复杂度是 O(1)，且可以保证在删除操作之后变长数组中的所有元素的下标都连续，
        // 方便插入操作和获取随机元素操作。
        nums.remove(nums.size() - 1);
        indices.remove(val);
        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(nums.size());
        return nums.get(randomIndex);
    }

}


