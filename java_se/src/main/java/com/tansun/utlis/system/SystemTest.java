package com.tansun.utlis.system;

import com.tansun.utlis.CommandUtils;

import java.io.IOException;
import java.util.Properties;

public class SystemTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        systemTest();
        systemEnv();

//        CommandUtils.run(false, null,"");

    }

    public static void systemTest(){
        // 运行环境
        System.out.println(System.getenv());
        System.out.println("------------------------");

        System.out.println(System.getProperties());
        System.out.println("------------------------");

        System.out.println(System.getSecurityManager());
        System.out.println("------------------------");

        System.out.println(System.getProperty("java.library.path"));
        System.out.println("------------------------");

        System.out.println(System.getProperty("java.class.path"));
        System.out.println("------------------------");
    }

    public static void systemEnv(){
        Properties props = System.getProperties();

        String osName = props.getProperty("os.name");
        String osArch = props.getProperty("os.arch");
        String osBit  = props.getProperty("sun.arch.data.model");
        String tmpDirPath = System.getProperty("java.io.tmpdir");
        System.out.println("os.name:" + osName + ", os.arch:" + osArch + ", sun.arch.data.model:" + osBit + ", java.io.tmpdir:" + tmpDirPath);
    }
}
