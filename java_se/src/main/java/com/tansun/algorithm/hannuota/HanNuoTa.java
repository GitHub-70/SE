package com.tansun.algorithm.hannuota;

/**
 * 汉诺塔问题
 *  1.每次只能移动柱子最顶端的一个圆盘；
 *  2.每个柱子上，小圆盘永远要位于大圆盘之上；
 *      汉诺塔问题是一个经典的问题。汉诺塔（Hanoi Tower），又称河内塔，源于印度一个古老传说。
 *      大梵天创造世界的时候做了三根金刚石柱子，在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。
 *      大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。并且规定，任何时候，
 *      在小圆盘上都不能放大圆盘，且在三根柱子之间一次只能移动一个圆盘。问应该如何操作？
 *
 *      https://blog.csdn.net/qq_52906742/article/details/115429407
 *      1.你会发现n个盘子所需的步数为2的n次方减一，并且最中间一步永远为A–>C
 *      2.为什么奇数个盘子时第一步永远为A–>C，而偶数个时，第一步永远为A–>B？
 *      hanoi函数，形参变化：ACB->ACB->ABC->BCA->ABC
 */
public class HanNuoTa {
    public static void main(String[] args) {
        hanoi(5,'A','C','B');
        System.out.println("总的移动了："+ (n-1) +"次");
    }

    //定义n来记录移动次数
    public static int n = 1;
    /**
     * 定义一个递归函数来实现汉诺塔的移动问题
     * @param num    汉诺塔层数
     * @param begin  开始柱
     * @param end    目标柱
     * @param middle 中转站
     */
    public static void hanoi(int num,char begin,char end,char middle){
        if(num == 1){
            //如果层数为1，直接将其移动到目标柱
            System.out.println("第"+ n +"次：从"+ begin +"移动到"+ end);
            n++;
        }else {
            //目前情况：begin上有num层，middle无，end无
            //把begin上面的num-1层移动到middle（这里把end和middle换位置，这样就把num-1层移到了middle上）
            hanoi(num-1,begin,middle,end);
            //目前情况：begin上有1层，middle上num-1有层，end无
            //把begin的那层移动到end
            System.out.println("第"+ n +"次：从"+ begin +"移动到"+ end);
            //目前情况：begin无，middle上有num-1层，end上有1层
            n++;
            //把middle的num-1层移动到end（这里把begin和middle换位置，这样就把num-1层移动到了end上）
            hanoi(num-1,middle,end,begin);
        }
    }
}
