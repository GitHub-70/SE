package com.tansun.file;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 实现多线程对同一个文件的并行读写
 *
 *
 * @author Mayber
 * @date Created on 2022/4/10
 */
public class ParalFile {



    public AtomicInteger index = new AtomicInteger(0);
    public long size;

    public int buffer_size;

    RandomAccessFile orign_raf;
    RandomAccessFile target_raf;
    FileChannel orignFileChannel;
    FileChannel targetFileChannel;

    public ParalFile(File orign,int buffer_size) throws FileNotFoundException {
        RandomAccessFile orign_raf = new RandomAccessFile(orign,"rw");
//         RandomAccessFile target_raf = new RandomAccessFile(target,"rw");
        this.orignFileChannel = orign_raf.getChannel();
//        this.targetFileChannel = target_raf.getChannel();
        this.buffer_size = buffer_size;
        this.size = orign.length();
        System.out.println("构造完毕");
    }


    class ReadTask implements Runnable{
        @Override
        public void run() {
            // 这个任务中需要使用cas获取到当前的 index，并且读取index+buffer值，然后将index改为
            int cur_index;
            System.out.println("执行");
            while((cur_index = index.get())<size){

                int target_index = (cur_index+buffer_size)>size? (int)size :cur_index+buffer_size;


                if(index.compareAndSet(cur_index,target_index+1)){
                    //如果cas 成功就进行读写操作

                    System.out.println(Thread.currentThread().getName()+"读取了cur_index"+cur_index+"到"+target_index+"的缓冲数据");
                    byte[] content = readFile(cur_index,target_index);

                    // 读取到了内容可以在下面进行一些别的处理操作

                }
            }

        }

        public byte[] readFile(int orign_index,int target_index){

            // 读取文件,使用一个map内存映射进行读取，可以加速读取吧
            MappedByteBuffer map;
            byte[] byteArr = new byte[target_index-orign_index];
            try {
                map = orignFileChannel.map(FileChannel.MapMode.READ_ONLY,orign_index,target_index-orign_index);

                map.get(byteArr,0,target_index-orign_index);
            } catch (Exception e) {
                System.out.println("读取"+orign_index+"到"+target_index+"失败");
                e.printStackTrace();
            }
            return byteArr;


        }


    }

    class WriteTask implements Runnable{

        @Override
        public void run() {

            byte[] a = new byte[1024];
            int cur_index;
            System.out.println("执行");
            while((cur_index = index.get())<size){

                int target_index = (cur_index+buffer_size)>size? (int)size :cur_index+buffer_size;


                if(index.compareAndSet(cur_index,target_index+1)){
                    //如果cas 成功就进行读写操作
                    //成功
                    System.out.println(Thread.currentThread().getName()+"写了cur_index"+cur_index+"到"+target_index+"的缓冲数据");
                    writeFile(cur_index,target_index,a);

                    // 读取

                }
            }
        }

        public void writeFile(int orign_index,int target_index,byte[] content){
            //然后进行写
            // 读取文件,使用一个map内存映射进行读取，可以加速读取吧
            MappedByteBuffer map;
            byte[] byteArr = new byte[target_index-orign_index];
            try {
                map = targetFileChannel.map(FileChannel.MapMode.READ_ONLY,orign_index,target_index-orign_index);

                map.position();
                map.put(content);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        File orign = new File("E:\\idea_workspace\\projecte-SpringBoot\\" +
                "CGB-DB-SYS-V3.03\\src\\main\\resources\\sql\\dbms_test.sql");
        ParalFile pf = new ParalFile(orign,30);
        ThreadPoolExecutor poll = new ThreadPoolExecutor(
                4,5,1,
                TimeUnit.HOURS,
                new LinkedBlockingDeque<>(10) );

        for(int i=0;i<5;i++){
            poll.execute(pf.new ReadTask());
        }



    }




}


