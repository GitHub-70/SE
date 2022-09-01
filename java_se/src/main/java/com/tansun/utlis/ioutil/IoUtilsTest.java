package com.tansun.utlis.ioutil;

import com.alibaba.fastjson.util.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IoUtilsTest {


    public static void main(String[] args) {
        String a = "国宝熊猫吃饭中...";
        try {
            FileInputStream fileInputStream = new FileInputStream(a);

        } catch (IOException e){

        }
    }
}
