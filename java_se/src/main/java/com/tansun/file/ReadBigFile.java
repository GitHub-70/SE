package com.tansun.file;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadBigFile {
    public static void main(String[] args) throws IOException {
        String url = "E:\\file\\20230309\\stringformat.txt";
        File f = new File(url);
        FileInputStream fis = new FileInputStream(f);// 2147483647
        FileChannel channel = fis.getChannel();

        long length = f.length();
        // 当你面临java.lang.OutOfMemoryError: Requested array size exceeds VM limit,
        // 意味着应用因为尝试分配一个大于JVM可以支持的数组而报错crash.
        int max = Integer.MAX_VALUE/2;

        // 文件大小 大于int最大值
        if (length > max) {
            // 取模计算次数
            int time = length % max > 0 ? (int) (length / max + 1) : (int) (length / max);
            for (int i = 0; i < time; i++) {

                if ((length - max) > 0) {
                    byte[] ds = new byte[max];
                    readFile(channel, max, ds);
                    length -= max;
                } else {
                    byte[] ds = new byte[(int) length];
                    readFile(channel, (int) length, ds);
                }
            }

        // 文件大小 小于int最大值
        } else {
            byte[] ds = new byte[(int) length];
            readFile(channel, (int) length, ds);
        }

        channel.close();
        fis.close();
    }

    private static void readFile(FileChannel channel, int length, byte[] ds) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(length);
        channel.read(buffer);
        buffer.flip();
        buffer.get(ds);
        String str = new String(ds);
        System.out.println("读取的字符串是：" + str);
    }
}