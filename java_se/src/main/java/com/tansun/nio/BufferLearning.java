package com.tansun.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;


/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName BufferTest
 * @date 2020/11/8 17:00
 * @Copyright © 2020 阿里巴巴
 */
public class BufferLearning {

    static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static byte[] bytes = new byte[byteBuffer.limit()];// 指定limit（）大小的数组
    private static Logger logger = LoggerFactory.getLogger(BufferLearning.class);

    /**
     * 非直接缓冲区：通过allocate（）方法获取一个buffer缓冲区，此缓冲区分配在jvm内存中
     * 直接缓冲区：通过allocateDirect（）方法获取一个buffer缓冲区，此缓冲区分配至机器的物理内存中
     * <p>
     * 缓冲区的四个核心属性：
     * capacity: 表示缓冲区的数据容量，一旦声明就不能改变
     * limit: 表示可操作缓冲区中的数据的界限大小。（分为写模式和读模式）
     * position: 表示缓冲区中开始(读/写)数据的位置
     * mark: 会持续记住此位置
     * mark <= position <= limit <= capacity
     */
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        allocate();
        put();
        get();
        rewind();
        reset();
        clear();
        isDirect();
    }

    /**
     * 分配缓冲区时的状态
     */
    private static void allocate() {

        logger.info("缓冲区大小capacity={}", byteBuffer.capacity());
        logger.info("写/(读)模式下,可操作缓冲区数据的界限limit={}", byteBuffer.limit());
        logger.info("此时指针位置position={}", byteBuffer.position());
        logger.info("用mark标记时的状态{}", byteBuffer.mark());
    }

    /**
     * 将数据放入缓冲区的状态
     */
    private static void put() {
        logger.info("---------------put()-----------------");
        String string = "abcdefghijklmnopqrstuvwxyz";
        logger.info("将String类型的数据放入缓冲区中，其内容为：{}", string);
        byteBuffer.put(string.getBytes());
        allocate();
    }

    /**
     * 获取指定类型 大小时的数据状态
     */
    private static void get() {
        logger.info("---------------get()-----------------");
        // 切换到读模式
        byteBuffer.flip();
        byteBuffer.get(bytes, 0, 5);
        logger.info("读取byte数组{}", bytes);
        allocate();
    }

    /**
     * 可重复读
     */
    private static void rewind() {
        logger.info("---------------rewind()-----------------");
        logger.info("重读前：byte数组中数据{}", bytes);
        byteBuffer.rewind();// 	将position位置设为为 0， 取消设置的 mark
        logger.info("重读后：此时指针position位置{}", byteBuffer.position());
        byteBuffer.get(bytes, 4, 8);//先在bytes重读4位，之后再从缓冲区中从position位置读取8位
        logger.info("此时指针position位置{}", byteBuffer.position());
        logger.info("byte数组中内容--------{}", bytes);
        logger.info("=======注意区别");
        byteBuffer.get(bytes, 11, 6);//先在bytes重读11位，之后再从缓冲区中从position位置读取6位
        logger.info("byte数组中内容--------{}", bytes);
//        byteBuffer.flip();
        allocate();
    }

    /**
     * 同一个模式下恢复到mark状态下
     */
    private static void reset() {
        logger.info("---------------reset()-----------------");
        logger.info("此时用mark标记一下");
        byteBuffer.mark();// mark之后，切换模式后，在reset会报错；除非再mark,再reset
        logger.info("此时指针position位置{}", byteBuffer.position());
        byteBuffer.get(bytes, 12, 5);
        logger.info("byte数组中内容--------{}", bytes);
        logger.info("此时指针positcin位置{}", byteBuffer.position());
        logger.info("此时reset()一下下", byteBuffer.reset());
        allocate();
    }

    /**
     * 清空缓冲区，但缓冲区数据依然存在
     * 只是状态被重置
     */
    private static void clear() {
        logger.info("---------------clear()-----------------");
        logger.info("此时clear()一下下，将所有状态恢复到初始时的状态");
        byteBuffer.clear();
        allocate();
        logger.info("clear()缓冲区后，仍有数据存在byteBuffer.get(0)={}不为空", byteBuffer.get());
        logger.info("clear()缓冲区后，仍有数据存在byteBuffer.get(2)={}不为空", byteBuffer.get(2));
        logger.info("切换到读模式前,个位置状态");
        allocate();
        logger.info("切换到读模式，调用flip()方法");
        byteBuffer.flip();
        logger.info("读模式下,可操作缓冲区数据的界限limit={}", byteBuffer.limit());
        allocate();
    }

    /**
     * 判断是否是直接缓冲区
     */
    private static void isDirect() {
        logger.info("---------------isDirect()-----------------");
        ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(1024);
        logger.info("byteBuffer.allocate()---{}---是直接缓冲区吗", byteBuffer.isDirect());
        logger.info("byteBuffer1.allocateDirect()---{}---是直接缓冲区吗", byteBuffer1.isDirect());
    }
}
