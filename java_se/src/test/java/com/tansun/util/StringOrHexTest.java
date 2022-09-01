package com.tansun.util;

import javax.xml.bind.DatatypeConverter;

public class StringOrHexTest {


    public static void main(String[] args) {
        String pwString = "姚明与奥利尔@x~.com";
        String printHexBinary = DatatypeConverter.printHexBinary(pwString.getBytes());
        byte[] binary = DatatypeConverter.parseHexBinary(printHexBinary);


        System.out.println("String转16进制："+ printHexBinary);
        System.out.println("String转16进制后大小："+ printHexBinary.getBytes().length);
        System.out.println("16进制转String："+ new String(binary));


        System.out.println("----------------------分割线--------------------");
        // https://www.cnblogs.com/twoheads/p/9722439.html
        // '\r'是回车，'\n'是换行，前者使光标到行首，后者使光标下移一格。

        // （windows系统）\r 就是return 回到 本行行首
        // 这就会把这一行以前的输出 覆盖掉
        System.out.println("回车：\n"+"aa\rbb");

        System.out.println("换行：\n"+"aa\ncc");// \n为换行
        System.out.println("退格：\n"+"aa\bdd");// \b为退格
        System.out.println("空字符：\n"+"aa\0dd");// \0为空格
        System.out.println("反斜杠：\n"+"aa\\dd");// 转义\
        System.out.println("TAB键：\n"+"aa\tdd");// \t为TAB键

    }
}
