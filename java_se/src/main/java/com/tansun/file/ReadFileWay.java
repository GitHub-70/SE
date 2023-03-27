package com.tansun.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 文件流
 */
public class ReadFileWay {

    public static void main(String[] args) throws IOException {
//        readAllLines();
//        readAllLine();
//        readLineByFileUtils();

//        appendDataToFile1();
//        appendDataToFile2();

        readLineByNum("",50);
    }

    /**
     * 一行一行的读取文件
     * @throws IOException
     *
     * JDK常用工具类：
     *  https://my.oschina.net/u/2463883/blog/508718
     */
    private static void readAllLines() throws IOException {
        File file = new File("E:\\文本文件\\redis常用命令.txt");
        List<String> fileList = Files.readAllLines(file.toPath());
        for (String line : fileList) {
            System.out.println(line);
        }
        // 文件绝对路径
        System.out.println(file.getAbsolutePath());
    }

    /**
     * 一行一行的读取文件
     * 文字就用字符流读取
     * @throws IOException
     */
    private static void readAllLine() throws IOException {
        FileReader fileReader = new FileReader("E:\\文本文件\\redis常用命令.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while (null != (line = bufferedReader.readLine())) {
            System.out.println(line);
        }
        if (null != bufferedReader){
            bufferedReader.close();
        }
        if (null != fileReader){
            fileReader.close();
        }
    }

    private static void readLineByFileUtils() throws IOException {
        File file = new File("E:\\文本文件\\redis常用命令.txt");
        LineIterator lineIterator = FileUtils.lineIterator(file);

        while (lineIterator.hasNext()){
            String line = lineIterator.nextLine();
            System.out.println(line);
        }
        //
        LineIterator.closeQuietly(lineIterator);
    }

    /**
     * 文件字节流
     * 在源文件上 进行内容覆盖
     * @throws IOException
     */
    public static void appendDataToFile1() throws IOException {

        File file = new File("E:\\file\\20220406\\stringformat.txt");
        // 判断其文件目录 是否存在
        if (!file.getParentFile().exists()){
            // 创建其文件 父级目录
            file.getParentFile().mkdirs();
            // 判断其文件 是否存在
            if (!file.exists()){
                file.createNewFile();
            }
        }
        // 不填默认是false，不追加，即覆盖源文件内容，true则表示在源文件上进行追加内容
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        for (int i = 0; i < 10; i++) {
            bufferedOutputStream.write("-------------------\n".getBytes());
            // 不主动调用该flush()方法，默认会在fileOutputStream结束时，主动一次性将全部buffer刷新到文件中
//            bufferedOutputStream.flush();

            // java格式化输出
            fileOutputStream.write(String.format("%-5s %s %6s %10s %10s\n", "姓名","年龄","身高","体重","体重指数").getBytes());

        }

        // buffered需要fos的支持，所以需要先判断关闭buffered
        if (null != bufferedOutputStream){
            bufferedOutputStream.close();
        }
        if (null != fileOutputStream){
            fileOutputStream.close();
        }


    }

    /**
     * 文件字符流
     * 在源文件上 进行追加内容
     * @throws IOException
     */
    public static void appendDataToFile2() throws IOException {

        File file = new File("E:\\file\\20220406\\stringformat.txt");
        // 判断其文件目录 是否存在
        if (!file.getParentFile().exists()){
            // 创建其文件 父级目录
            file.getParentFile().mkdirs();
            // 判断其文件 是否存在
            if (!file.exists()){
                file.createNewFile();
            }
        }
        // 不填默认是false，不追加，即覆盖源文件内容，true则表示在源文件上进行追加内容
        FileWriter fileWriter = new FileWriter(file, true);
        // 将数据读取到bufferc缓冲区中
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < 10; i++) {
            // java格式化输出
            fileWriter.write(String.format("%-5s %s %6s %10s %10s\n", "姓名","年龄","身高","体重","体重指数"));
        }

        if (null != fileWriter){
            fileWriter.close();
        }

    }


    /**
     * 从文件中，指定行数读取数据
     * @param txtPath 文件路径
     * @param lineNum 行号
     * @throws IOException
     */
    public static void readLineByNum(String txtPath,int lineNum) throws IOException {
        FileWriter writer;
        //文件总行数
        txtPath = "E:\\文本文件\\redis常用命令.txt";
        long count1 = Files.lines(Paths.get(txtPath)).count();
        System.out.println("文件总行数："+count1);

        StringBuffer sb = new StringBuffer();
        LineNumberReader lnr = new LineNumberReader(new FileReader(txtPath));
        String line = lnr.readLine();

        while (line!=null){
            // 大于等于起始行，小于等于结束行
            if (lnr.getLineNumber() >= lineNum && lnr.getLineNumber()<=count1-2){
                sb.append(line);
                sb.append("\r\n");
            }
            // 拼接结束，在读取下一行
            line = lnr.readLine();
        }
        String newTxtPath = "E:\\file\\20230309\\stringformat.txt";
        File file = new File(newTxtPath);
        if (!file.getParentFile().exists()){
            file.getParentFile().exists();
            if (!file.exists()){
                file.createNewFile();
            }
        }

        writer = new FileWriter(newTxtPath,false);
        writer.write(sb.toString());
        writer.close();
        System.out.println(sb.toString());
    }

}
