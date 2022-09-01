package com.tansun.thread.pool;

import com.tansun.thread.pool.Task.ThreadTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Executor;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExecutorTest.class)
public class ExecutorTest {


    @Autowired(required=true)
    private ThreadPoolExecutors threadPoolExecutors;

    private static String executorType;


    @Test
    public void getExecutor(){
        executorType = "fixedExecutor";
        // 创建一个固定大小的线程池
        Executor fixedExecutor  = threadPoolExecutors.getThreadExecutorType(executorType);

        for (int i = 0; i < 5; i++) {
            fixedExecutor.execute(new ThreadTask());
        }
    }
}
