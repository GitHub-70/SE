package com.tansun.algorithm.leetcode.arrayorstring;

/**
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。
 * 计算并返回该研究者的 h 指数。
 * <p>
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，
 * 并且 至少 有 h 篇论文被引用次数大于等于 h 。如果 h 有多种可能的值，h 指数 是其中最大的那个。
 * <p>
 * 示例：
 * 输入：citations = [4,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 4, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 */
public class HIndex {

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
//        int[] citations = {1,3,1};
        System.out.println(hIndex(citations));
        System.out.println(hIndex2(citations));

    }


    /**
     * 计数排序
     * 计算科研工作者的H指数。
     * H指数的定义: 一个科研工作者的H指数是指他（她）的N篇论文中有H篇论文分别被引用了至少H次。
     * 其余的N-H篇论文每篇被引用次数不超过H次。
     *
     * @param citations 一个整数数组，表示每篇论文的引用次数。
     * @return 返回该科研工作者的H指数。
     */
    public static int hIndex(int[] citations) {
        // 计算论文数量
        int len = citations.length;
        // 如果没有论文，则H指数为0
        if (len == 0) {
            return 0;
        }
        // 初始化计数数组，长度为len+1，用于记录每个引用次数出现的频率，
        // 引用次数放入对应的下标，对引用次数进行统计
        int[] count = new int[len + 1];
        for (int i = 0; i < len; i++) {
            // 如果引用次数大于论文数量，则将其归入最后一组（即len这一组）
            if (citations[i] > len) {
                count[len]++;

                // 如果引用次数不超过论文数量，则将其归入相应的组
            } else {
                count[citations[i]]++;
            }
        }

        // i是论文的引用次数，值对应的篇数
        int total = 0;// 记录引用次数的论文总数
        for (int i = len; i >= 0; i--) {
            // 累加当前引用次数的论文数量
            total += count[i];
            // 如果累加的总数大于等于当前引用的次数 i有 total 篇，则说明，至少有 I 篇被引用超过 I次;说明H指数就为 I
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 二分查找
     * h 指数的取值[0,n]
     * 时间复杂度：O(nlogn)，其中 n 为数组 citations 的长度。需要进行 logn 次二分搜索，
     *                      每次二分搜索需要遍历数组 citations 一次。
     * 空间复杂度：O(1)，只需要常数个变量来进行二分搜索。
     * @param citations
     * @return
     */
    public static int hIndex2(int[] citations) {
        int left=0,right=citations.length;
        int mid=0,cnt=0; // 分别用于存储中间位置的值和计数器
        while(left<right){
            // 这里>> 1是对除以2的快速位运算，+1是为了避免死循环发生在应该取右区间的情况
            mid=(left+right+1)>>1;
            cnt=0;
            for(int i=0;i<citations.length;i++){
                if(citations[i]>=mid){
                    cnt++;
                }
            }
            if(cnt>=mid){
                // 要找的答案在 [mid,right] 区间内
                left=mid;
            }else{
                // 要找的答案在 [0,mid) 区间内
                right=mid-1;
            }
        }
        return left;
    }

}
