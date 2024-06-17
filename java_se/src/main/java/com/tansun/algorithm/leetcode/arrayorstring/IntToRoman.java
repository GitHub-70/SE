package com.tansun.algorithm.leetcode.arrayorstring;

public class IntToRoman {

    public static void main(String[] args) {
        int num = 1994;
        System.out.println(intToRoman(num));
        System.out.println(intToRoman2(num));
    }

    public static String intToRoman2(int num) {
        int[] arr = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
        String[] romanNum = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
        StringBuilder sb = new StringBuilder();

        for (int i = arr.length-1; i >= 0 ; i--) {
            if(num/arr[i] != 0){
                sb.append(romanNum[i]);
                num = num - arr[i];
                ++i;
            }
        }
        return sb.toString();

    }


    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num >= 1000) {
            sb.append("M");
            num -= 1000;
        }
        while (num >= 900) {
            sb.append("CM");
            num -= 900;
        }
        while (num >= 500) {
            sb.append("D");
            num -= 500;
        }
        while (num >= 400) {
            sb.append("CD");
            num -= 400;
        }
        while (num >= 100) {
            sb.append("C");
            num -= 100;
        }
        while (num >= 90) {
            sb.append("XC");
            num -= 90;
        }
        while (num >= 50) {
            sb.append("L");
            num -= 50;
        }
        while (num >= 40) {
            sb.append("XL");
            num -= 40;
        }
        while (num >= 10) {
            sb.append("X");
            num -= 10;
        }
        while (num >= 9) {
            sb.append("IX");
            num -= 9;
        }
        while (num >= 5) {
            sb.append("V");
            num -= 5;
        }
        while (num >= 4) {
            sb.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            sb.append("I");
            num -= 1;
        }
        return sb.toString();
    }
}
