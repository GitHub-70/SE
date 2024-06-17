package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。
 * 如果存在解，则 保证 它是 唯一 的。
 *
 * 示例 1:
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 示例 2:
 * 输入: gas = [2,3,4], cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 */
public class CanCompleteCircuit {

    public static void main(String[] args) {
//        int[] gas = {1,2,3,4,5};int[] cost = {3,4,5,1,2};
        int[] gas = {2,0,0,0,0};int[] cost = {0,1,0,0,0};
        System.out.println(canCompleteCircuit(gas,cost));
    }

    /**
     * 有一个环形路上有n个站点； 每个站点都有一个好人或一个坏人； 好人会给你钱，坏人会收你一定的过路费，
     * 如果你带的钱不够付过路费，坏人会跳起来把你砍死； 问：从哪个站点出发，能绕一圈活着回到出发点?
     *
     * 首先考虑一种情况：如果全部好人给你 的钱加起来 小于 坏人收的过路费之和，那么总有一次你的钱不够付过路费，
     * 你的结局注定会被砍死。
     *
     * 假如你随机选一点 start 出发，那么你肯定会选一个有好人的站点开始，因为开始的时候你没有钱，
     * 遇到坏人只能被砍死；
     *
     * 现在你在start出发，走到了某个站点end，被end站点的坏人砍死了，说明你在 [start, end) 存的钱不够
     * 付 end点坏人的过路费，因为start站点是个好人，所以在 (start, end) 里任何一点出发，
     * 你存的钱会比现在还少，还是会被end站点的坏人砍死；
     *
     * 于是你重新读档，聪明的选择从 end+1点出发，继续你悲壮的征程； 终于有一天，你发现自己走到了尽头
     * （下标是n-1)的站点而没有被砍死； 此时你犹豫了一下，那我继续往前走，身上的钱够不够你继续走到出发点Start?
     *
     * 当然可以，因为开始已经判断过，好人给你的钱数是大于等于坏人要的过路费的，
     * 你现在攒的钱完全可以应付 [0, start) 这一段坏人向你收的过路费。 这时候你的嘴角微微上扬，
     * 眼眶微微湿润，因为你已经知道这个世界的终极奥秘：Start就是这个问题的答案。
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost){
        int n = gas.length;
        int gasTotal = 0;
        int startIndex = 0;
        // 贪心算法，判断是否可以完成一圈
        for (int i = 0; i < n; i++) {
            gasTotal += gas[i] - cost[i];
        }
        if (gasTotal < 0){
            return -1;
        }
        // 当前油箱油量
        int currentGas = 0;
        for (int i = 0; i < n; i++) {
            currentGas += gas[i] - cost[i];
            if (currentGas < 0){
                startIndex = i+1;
                currentGas = 0;
            }
        }
        return startIndex;
    }

    /**
     * 暴力算法
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit2(int[] gas, int[] cost){
        int n = gas.length;
        int startIndex = -1;
        for (int i = 0; i < n; i++) {
            // tank 油箱
            int tank = 0;
            for (int j = i; j < i + n; j++) {
                tank += gas[j % n] - cost[j % n];
                if (tank < 0) {
                    break;
                }
            }
            if (tank >= 0) {
                startIndex = i;
                break;
            }
        }
        return startIndex;
    }

}
