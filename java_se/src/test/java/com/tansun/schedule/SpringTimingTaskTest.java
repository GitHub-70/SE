package com.tansun.schedule;

import com.tansun.pool.MyDefinedThreadPoolExecutor;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.ThreadPoolExecutor;

public class SpringTimingTaskTest {

    @Test
    public void doTaskTest(){
//        scheduleTaskTest();
        // 空指针异常
        SpringTimingTask.doTask();
    }

    private void scheduleTaskTest() {
        // 加载MyDefinedThreadPoolExecutor类
        ServiceLoader<MyDefinedThreadPoolExecutor> serviceLoader = ServiceLoader.load(MyDefinedThreadPoolExecutor.class);
        // TODO 空指针异常--MyDefinedThreadPoolExecutor类，未被加载，内存中不存在？
        SpringTimingTask.doTask();
        Iterator<MyDefinedThreadPoolExecutor> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            MyDefinedThreadPoolExecutor myDefinedThreadPoolExecutor = iterator.next();
            //  TODO 能调getThreadPoolExecutor()方法，为何方法列表里没有
            myDefinedThreadPoolExecutor.getThreadPoolExecutor();
        }
    }
}
