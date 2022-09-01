package com.tansun.file;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @ClassName FileRenameToTest
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/6/28 19:18
 * @Copyright © 2020 阿里巴巴
 */
public class FileRenameToTest {

    public static void main(String[] args) {

        File file1 = new File("E:\\file\\test\\back\\a.txt");
        if (!file1.exists()){
            try {
                boolean newFile = file1.createNewFile();
                if (newFile){
                    Path path = Paths.get(file1.getAbsolutePath());
                    Charset charset = Charset.forName("UTF-8");
                    BufferedWriter bufferedWriter = Files.newBufferedWriter(path, charset, StandardOpenOption.APPEND);
                    bufferedWriter.write("abcdefg");
                    bufferedWriter.flush();
                }
            } catch (IOException e){
                System.out.println("创建文件异常，文件路劲可能已经不存在！");
                System.out.println(e);
            }
        }
        File file2 = new File("E:\\file\\test\\b.txt");
        if (file2.exists()){
            file2.delete();
        }
        // file1文件不存在时 或者 file2存在时，即会重命名失败，结果为false
        System.out.println(file1.renameTo(file2));
        System.out.println(file1);

        // 文件大小
        System.out.println("file1文件长度：" + file1.length());
        System.out.println("file2文件长度：" + file2.length());


        System.out.println("file1绝对路径："+ file1.getAbsolutePath());
        // getAbsoluteFile()底层调用了 getAbsolutePath()
        System.out.println("file1绝对路径："+ file1.getAbsoluteFile());

        //路径分隔符 windows:分号;  linux:冒号:
        String pathseparator = File.pathSeparator;
        System.out.println(pathseparator);

        // 文件名称分隔符 windows:反斜杠\  linux:正斜杠/
        String separator = File.separator;
        System.out.println(separator);


//        String c = "aa.bb";
//        System.out.println(c.substring(c.indexOf(".")+1));
    }
}
