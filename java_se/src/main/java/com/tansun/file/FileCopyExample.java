package com.tansun.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 嘎嘎快。。。6/7G 可能30秒
 */
public class FileCopyExample {
    public static void main(String[] args) {
        String sourceFile = "E:\\file\\testdelete\\cfmfile.txt";
        String targetFile = "E:\\file\\testdelete\\cfmfilecopy.txt";

        try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
             FileChannel targetChannel = new FileOutputStream(targetFile).getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 100); // 1MB buffer

            while (sourceChannel.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    targetChannel.write(buffer);
                }
                buffer.clear();
            }

            System.out.println("File copied successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

