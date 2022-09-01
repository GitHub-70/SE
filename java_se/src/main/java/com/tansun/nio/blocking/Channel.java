package com.tansun.nio.blocking;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName NioBlocking
 * @date 2020/11/9 3:41
 * @Copyright © 2020 阿里巴巴
 */

import com.tansun.utlis.UrlUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * https://blog.csdn.net/forezp/article/details/88414741
 *
 * 通道（Channel）的主要实现类
 * java.nio.channels.channel接口
 * |--FileChannel              本地
 * |--SocketChannel            网络-TCP
 * |--ServerSocketChannel      网络-TCP
 * |--DatagramChannel          网络-UDP
 * <p>
 * 获取通道方式：
 * 1.getChannel()     ---RandomAccessFile的getChannel()  流的getChannel()方法
 * 2.JDK1.7中的NIO.2的静态方法open()                    通道的open()方法
 * 3.JDK1.7中的NIO.2的Files工具类的newByteChannel()
 * <p>
 * 网络IO
 * Socket
 * ServerSocket
 * DatagramSocket
 * <p>
 * 分配指定大小内存空间方法
 *          Buffer的 allocate()          ---非直接缓冲区
 *          Buffer的 allocateDirect()    ---直接缓冲区
 *      返回值类型是MappedByteBuffer
 *         通道的 map()                  ---直接缓冲区
 *      通道之间数据传输
 *          通道的 transferTo()          ---直接缓冲区
 *          通道的 transferFrom()        ---直接缓冲区
 * <p>
 * 分散Scattering与聚集Gathering
 * 分散读取：将通道中的数据分散到多个缓冲区中
 * 聚集写入：将多个缓冲区中的数据写入到通道中
 * <p>
 * 字符集：Charset
 */
public class Channel {

    private static String url = "E:/xjpic.jpg";


    public static void main(String[] args) throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();
//        getChannelCopy();
//        getOpen();
        getOpenTransfer();
//        scatterAndGather();
//        getCharset();
//        System.out.println(url.substring(3));
    }

    /**
     * 字符集
     */
    private static void getCharset() {
        Map<String, Charset> charsetMap = Charset.availableCharsets();
        // 将Map放入Set集合中
        Set<Map.Entry<String, Charset>> charset = charsetMap.entrySet();
        // 遍历Set
        for (Map.Entry<String, Charset> stringCharsetEntry : charset) {
            System.out.println(stringCharsetEntry.getKey() + "=" + stringCharsetEntry.getValue());
        }

    }

    /**
     * 分散与聚集
     */
    private static void scatterAndGather() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(url, "rw");

        //获取本地文件输入通道
        FileChannel infileChannel = randomAccessFile.getChannel();
        // 分配指定大小的缓冲区
        ByteBuffer bufferA = ByteBuffer.allocate(100);
        ByteBuffer bufferB = ByteBuffer.allocate(1024);
        // 为分散读取准备数据
        ByteBuffer[] byteBuffers = {bufferA, bufferB};
        // 分散读取 todo
        while (infileChannel.read(byteBuffers) != -1) {
            for (ByteBuffer byteBuffer : byteBuffers) {
                byteBuffer.flip();
                infileChannel.read(byteBuffer);
                byteBuffer.clear();
            }
        }
        System.out.println(new String(byteBuffers[0].array(), 0, byteBuffers[0].limit()));
        System.out.println("-------------------------------");
        System.out.println(new String(byteBuffers[1].array(), 0, byteBuffers[1].limit()));
        // 聚集写入
        RandomAccessFile randomAccessFile1 = new RandomAccessFile(UrlUtils.getInputUrls(), "rw");
        // 获取本地文件输出通道
        FileChannel outfileChannel = randomAccessFile1.getChannel();

        outfileChannel.write(byteBuffers);
        // 关闭通道
        outfileChannel.close();
        infileChannel.close();
    }


    /**
     * 使用直接缓冲区（内存文件映射）的方式完成文件复制
     */
    private static void getOpenTransfer() throws IOException {
        // 获取输入输出通道
        FileChannel inPutChannel = FileChannel.open(Paths.get(url), StandardOpenOption.READ);
        FileChannel outPutChannel = FileChannel.open(Paths.get(UrlUtils.getInputUrls()), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        // 方式一
        // 分配直接缓冲区
//        inPutChannel.transferTo(0, inPutChannel.size(), outPutChannel);
//        inPutChannel.close();
//        outPutChannel.close();

        // 方式二
        outPutChannel.transferFrom(inPutChannel, 0, inPutChannel.size());
        outPutChannel.close();
        inPutChannel.close();
    }

    /**
     * 使用直接缓冲区（内存文件映射）的方式完成文件复制
     */
    private static void getOpen() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        int start = localDateTime.getNano();
        System.out.println(start);
        long start1 = System.currentTimeMillis();
        System.out.println(start1);
        // 获取本地文件输入通道
        FileChannel fileInputChannel = FileChannel.open(Paths.get(url), StandardOpenOption.READ);
        String inputUrl = UrlUtils.getInputUrls();
        // 支持读写模式 CREATE_NEW-没有就新建，有就报错
        FileChannel fileOutputChannel = FileChannel.open(Paths.get(inputUrl), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        // 内存映射，分配直接缓冲区
        MappedByteBuffer inMappedBBF = fileInputChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileInputChannel.size());
        MappedByteBuffer outMappedBBF = fileOutputChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileInputChannel.size());

        // 直接对 直接缓冲区进行读写操作
        byte[] bytes = new byte[inMappedBBF.limit()];
//        byte[] bytes = new byte[inMappedBBF.position()]; // 文件被损坏 TODO
        inMappedBBF.get(bytes);
        outMappedBBF.put(bytes);

        fileOutputChannel.close();
        fileInputChannel.close();
        int end = localDateTime.getNano();
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
        System.out.println(end - start);
    }

    /**
     * 非直接缓冲区的方式，完成文件复制（上传）
     */
    private static void getChannelCopy() {
        // 获取文件流
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        // 获取通道
        FileChannel fileInputChannel = null;
        FileChannel fileOutputChannel = null;
        String inputUrlFinal = UrlUtils.getInputUrls();

        try {
            fileInputStream = new FileInputStream(url);
            fileOutputStream = new FileOutputStream(inputUrlFinal);
            fileInputChannel = fileInputStream.getChannel();
            fileOutputChannel = fileOutputStream.getChannel();

            // 分配1024大小的缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 通道载入缓冲区中的数据
            while (fileInputChannel.read(byteBuffer) != -1) {
                // 读取byteBuffer后position位置 limit操作大小
                System.out.println(byteBuffer.position());
                System.out.println(byteBuffer.limit());
                System.out.println("=====================");
                byteBuffer.flip(); // 底层：将缓冲区 可操作的数据界限 设置为当前位置，并将当前位置充值为 0
//                byteBuffer.rewind(); // 这种也可以，但不知会有什么问题 --影响性能，操作数据只需操作到limit位置就可以了，此时的limit位置还在capacity位置
//                byteBuffer.clear(); // 这种也可以，但不知会有什么问题 TODO 同上 rewind()方法
                System.out.println(byteBuffer.position());
                System.out.println(byteBuffer.limit());
                // 通道将输出缓冲区数据
                fileOutputChannel.write(byteBuffer);
                byteBuffer.clear(); // 将缓冲区的界限设置为容量大小，并将当前位置充值为 0
//                byteBuffer.rewind();// 这种也可以，但不知会有什么问题 --下次读数据时，只有limit大小，不能达到capacity，相当于限制了Buffer的大小
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileOutputChannel)
                    fileOutputChannel.close();
                if (null != fileInputChannel)
                    fileInputChannel.close();
                if (null != fileOutputStream)
                    fileOutputStream.close();
                if (null != fileInputStream)
                    fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
