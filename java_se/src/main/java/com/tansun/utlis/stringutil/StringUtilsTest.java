package com.tansun.utlis.stringutil;



import com.sun.deploy.util.StringUtils;
import org.springframework.expression.EvaluationContext;

import java.util.ArrayList;
import java.util.List;

public class StringUtilsTest {

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();

        stringList.add("redisKey");
        stringList.add("redisValue1");
        stringList.add("redisValue2");
        System.out.println("stringList:" + stringList);

        String res = StringUtils.join(stringList,"-");
        System.out.println("res:" + res);

//        new EvaluationContext()
    }


}
