package com.tansun.processoptimize;

public class ForTest {

    /**
     * https://www.cnblogs.com/caoxb/p/15526123.html
     * 对于程序而言，外层循环越大，性能越低，对于数据库而言，永远是小的数据集放在最外层
     * 程序设计原则：小的循环放到最外层，大的循环放在最小层
     * @param args
     */

    public static void main(String[] args) {
        test6();
        test5();
        test4();
        test3();
        test2();
        test1();
    }

    public static void test1() {
        int x = 10;
        int y = 10000;
        int z = 10000000;

        long nanoTime1 = System.currentTimeMillis();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int m = 0; m < z; m++) {
                    int a = 10;
                    int b = 20;
                    int c = a + b;
                }
            }
        }
        long nanoTime2 = System.currentTimeMillis();
        System.out.println("test1总耗时:" + (nanoTime2 - nanoTime1));
    }

    public static void test2() {
        int x = 100;
        int y = 10000;
        int z = 1000000;

        long nanoTime1 = System.currentTimeMillis();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int m = 0; m < z; m++) {
                    int a = 10;
                    int b = 20;
                    int c = a + b;
                }
            }
        }
        long nanoTime2 = System.currentTimeMillis();
        System.out.println("test2总耗时:" + (nanoTime2 - nanoTime1));
    }

    public static void test3() {
        int x = 1000;
        int y = 10000;
        int z = 100000;

        long nanoTime1 = System.currentTimeMillis();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int m = 0; m < z; m++) {
                    int a = 10;
                    int b = 20;
                    int c = a + b;
                }
            }
        }
        long nanoTime2 = System.currentTimeMillis();
        System.out.println("test3总耗时:" + (nanoTime2 - nanoTime1));
    }

    public static void test4() {
        int x = 10000;
        int y = 10000;
        int z = 10000;

        long nanoTime1 = System.currentTimeMillis();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int m = 0; m < z; m++) {
                    int a = 10;
                    int b = 20;
                    int c = a + b;
                }
            }
        }
        long nanoTime2 = System.currentTimeMillis();
        System.out.println("test4总耗时:" + (nanoTime2 - nanoTime1));
    }

    public static void test5() {
        int x = 100000;
        int y = 10000;
        int z = 1000;

        long nanoTime1 = System.currentTimeMillis();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int m = 0; m < z; m++) {
                    int a = 10;
                    int b = 20;
                    int c = a + b;
                }
            }
        }
        long nanoTime2 = System.currentTimeMillis();
        System.out.println("test5总耗时:" + (nanoTime2 - nanoTime1));
    }

    public static void test6() {
        int x = 1000000;
        int y = 10000;
        int z = 100;

        long nanoTime1 = System.currentTimeMillis();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int m = 0; m < z; m++) {
                    int a = 10;
                    int b = 20;
                    int c = a + b;
                }
            }
        }
        long nanoTime2 = System.currentTimeMillis();
        System.out.println("test6总耗时:" + (nanoTime2 - nanoTime1));
    }
}
