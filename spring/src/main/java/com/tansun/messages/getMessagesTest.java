package com.tansun.messages;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Locale;

public class getMessagesTest {

    public static void main(String[] args) {
        /**
         * class path resource [properties/application.xml] cannot be opened because it does not exist
         *   配置的路径没问题
         *   程序运行，寻找的是 class 文件，于是找目标文件 target/classes 下，没有配置文件，
         *   TODO
         */
        MessageSource springContext = new ClassPathXmlApplicationContext("/properties/application.xml");
        Object[] agr = {"happy", Calendar.getInstance().getTime()};

        String userinfo = springContext.getMessage("userinfo", agr, Locale.CHINA);
        System.out.println("userinfo--->"+userinfo);

        String userinfoMasg = springContext.getMessage("userinfoMasg", agr, Locale.US);
        System.out.println("userinfoMasg--->"+userinfoMasg);

        String userinfoMessages = springContext.getMessage("userinfoMessages", agr, Locale.CHINA);
        System.out.println("userinfoMessages--->"+userinfoMessages);
    }
}
