package com.tansun.utlis.system;

import com.tansun.utlis.CommandUtils;

import java.io.IOException;

public class SystemTest {
    public static void main(String[] args) throws IOException, InterruptedException {
//        systemTest();

        CommandUtils.run(false, null,"");

    }

    public static void systemTest(){
        System.out.println(System.getenv());
        System.out.println("------------------------");
        System.out.println(System.getProperties());
        System.out.println("------------------------");
        System.out.println(System.getSecurityManager());
    }
}
