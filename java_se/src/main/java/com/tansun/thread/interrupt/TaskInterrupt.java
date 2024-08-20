package com.tansun.thread.interrupt;

import java.io.*;

public class TaskInterrupt {

    public static void main(String[] args) {

        Thread fileReaderThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader("largefile.txt"))) {
                String line;
                while ((line = reader.readLine()) != null && !Thread.currentThread().isInterrupted()) {
                    // 处理每一行数据
                    System.out.println(line);
                    try {
                        // 模拟处理数据
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // 如果在处理数据时发生中断，则重新设置中断状态
                        Thread.currentThread().interrupt();
                        System.out.println("Interrupted while processing line: " + line);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                // 重新设置中断状态，如果是因为中断引起的异常
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                // 在finally块中再次检查中断状态，确保在任何异常情况下都能正确处理中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("File reading was interrupted.");
                    // 继续处理中断后的后续操作
                }
            }
        });

        fileReaderThread.start();

        try {
            // 假设我们想在2秒后中断读取过程
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 请求中断线程
        fileReaderThread.interrupt();

        // 等待线程完全终止
        try {
            fileReaderThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
