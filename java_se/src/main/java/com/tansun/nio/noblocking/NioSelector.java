package com.tansun.nio.noblocking;

import com.tansun.utlis.UrlUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * @author 吴槐
 * @version V1.0
 * @Description 多路复用与异步
 * @ClassName NioSelector
 * @date 2020/11/14 2:39
 * @Copyright © 2020 阿里巴巴
 */
public class NioSelector {

    /**
     * 客户端
     */
    public void clien() throws IOException {
        // 1.获取客户端Socket通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        // 2.将通道切换成非阻塞
        socketChannel.configureBlocking(false);
        // 3.分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 4.通过FileChannel读取本地文件，再通过SocketChannel发送到服务端
        FileChannel inFileChannel = FileChannel.open(Paths.get(UrlUtils.getUrl()), StandardOpenOption.READ);

//        Scanner input = new Scanner(System.in);

        // 当用户有输入时
//        while (input.hasNext()){
//            String inputString = input.next();
//            Date date = new Date();
//            String cureentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//            byteBuffer.put((cureentTime+"\n"+inputString).getBytes());
//            byteBuffer.flip();
//            // 将输入的信息通过Socket发送到服务端
//            socketChannel.write(byteBuffer);
//            byteBuffer.clear();
//        }

        while (inFileChannel.read(byteBuffer) != -1){
            byteBuffer.flip(); // 读写模式切换
            socketChannel.write(byteBuffer);
            byteBuffer.clear();// 将Buffer的容量恢复至capacity，即将 可操作数据limit大小 恢复到capacity位置。
        }
        // 5.关闭通道
        socketChannel.close();
//        inFileChannel.close();
    }

    /**
     * 服务端
     */
    public void server() throws IOException{
        // 1.获取ServerSocket服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2.将通道切换成非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 3.通道绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9898));
        // 4.获取选择器
        Selector selector = Selector.open();
        // 5.将通道注册到选择器上，并且设置监听状态的事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);// 监听的是客户端的连接状态
        // 6.获取选择器上的已经“准备就绪”的事件
        while (selector.select() > 0){
            // 7.获取选择器中,所有就绪事件SelectionKey的迭代器
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 8.以轮询的方式，获取就绪事件
            while (iterator.hasNext()){
                // 获取就绪事件
                SelectionKey selectionKey = iterator.next();
                // 9.判断事件的状态，如果是“连接就绪”就接收客户端的连接
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 9.1.将客户端连接通道切换成非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 9.2将客户端的连接就绪状态的通道 注册到选择器上，并设置监听为读状态事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    // 10.如果是“读就绪”状态
                }else if (selectionKey.isReadable()){
                    // 11.获取就绪事件selectionKey的通道
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    // 12.分配指定大小的缓冲区
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    // 13.通过本地文件通道，读取通道中缓存的数据
                    //CREATE_NEW --会报错，已经存在 todo
                    FileChannel ineFileChannel = FileChannel.open(Paths.get(UrlUtils.getInputUrls()), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
//                    RandomAccessFile randomAccessFile = new RandomAccessFile(UrlUtils.getInputUrls(),"rw");
//                    FileChannel ineFileChannel = randomAccessFile.getChannel();
                    int len = -1;
                    while ((len = socketChannel.read(byteBuffer)) != -1){
                        byteBuffer.flip();// 读写模式切换
                        ineFileChannel.write(byteBuffer);
//                        System.out.print(new String(byteBuffer.array(), 0, len));// 输出到控制台
                        // 将Buffer的容量恢复至capacity，即将 可操作数据limit大小 恢复到capacity位置
                        byteBuffer.clear();
                    }
                }
                // 移除处理完的就绪事件selectionKey
                iterator.remove();
            }
        }
    }



}
