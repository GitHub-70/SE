package com.tansun.nio;

import com.tansun.nio.noblocking.NioSelector;
import com.tansun.nio.blocking.NioBlocking01;
import com.tansun.nio.blocking.NioBlocking02;
import org.junit.Test;

import java.io.IOException;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName BlockingNioTest
 * @date 2020/11/14 18:24
 * @Copyright © 2020 阿里巴巴
 */
public class BlockingNioTest {
    private static NioBlocking01 nioBlocking01;
//    @Autowired todo 该注入是通过set方法进行注入，set方法是对象的方法，而static属于类
    private static NioBlocking02 nioBlocking02;

    private static NioSelector nioSelector;
    static{
        if (null == nioBlocking01)
            nioBlocking01 = new NioBlocking01();
        if (null == nioBlocking02)
            nioBlocking02 = new NioBlocking02();
        if (null == nioSelector)
            nioSelector = new NioSelector();
    }

    /**
     * 客户端
     */
    @Test
    public void clien() throws IOException {
//        nioBlocking01.client();
//        nioBlocking02.client();
        nioSelector.clien();
    }

    /**
     * 服务端
     */
    @Test
    public void server() throws IOException{
//        nioBlocking01.server();
//        nioBlocking02.server();
        nioSelector.server();
    }
}
