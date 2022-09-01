package com.tansun.nio.blocking;


import com.tansun.utlis.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 通道（Channel）的主要实现类
 * java.nio.channels.channel接口
 * |--FileChannel              本地
 * |--SocketChannel            网络-TCP
 * |--ServerSocketChannel      网络-TCP
 * |--DatagramChannel          网络-UDP
 * 缓冲区：负责数据的存储
 * <p>
 * 选择器：负责监控通道的状态。
 *      是SelecttableChannel的多路复用器
 */


/**
 * @author 吴槐
 * @version V1.0
 * @Description 模拟服务端一直等待 客户端发送数据   同歩阻塞IO（BIO）
 * @ClassName NioBlocking
 * @date 2020/11/14 2:42
 * @Copyright © 2020 阿里巴巴
 */
@Service
public class NioBlocking02 {

    private static final Logger logger = LoggerFactory.getLogger(NioBlocking02.class);

    /**
     * 客户端
     */
    public void client() throws IOException {
        // 1.获取客户端Socket通道，连接到服务端
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        // 2.分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        FileChannel inChannel = FileChannel.open(Paths.get(UrlUtils.getUrl()), StandardOpenOption.READ);
        // 3.读取本地文件，并发送到服务端
        while (inChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        // 关闭远程连接
        socketChannel.shutdownOutput();

        // 4.模拟客户端通过Socket通道 接收服务端的反馈
        int len = 0;
        while ((len = socketChannel.read(byteBuffer)) != -1){
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, len));
            byteBuffer.clear();
        }

        // 5.关闭通道
        socketChannel.close();
        inChannel.close();
    }

    /**
     * 服务端
     */
    public void server() throws IOException{
        // 1.获取服务端ServerSocket通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2.绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));
        // 3.接收客户的通道
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 4.分配指定大小的缓冲区，准备接收客户端数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 5.读取缓冲区中的数据，保存到本地
        FileChannel outfileChannel = FileChannel.open(Paths.get(UrlUtils.getInputUrls()), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
        while (socketChannel.read(byteBuffer) != -1){
            byteBuffer.flip();
            outfileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        logger.info("客户端未及时连接服务端 发送数据时，是否会及时输出这句");

        // 6.模拟 服务端通过Socket通道，发送消息反馈给客户端
        byteBuffer.put("服务端接收数据成功".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        // 7.关闭通道
        outfileChannel.close();
        socketChannel.close();
        serverSocketChannel.close();
    }

}
