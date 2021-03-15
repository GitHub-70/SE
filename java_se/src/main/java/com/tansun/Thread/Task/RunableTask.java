package com.tansun.Thread.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 吴槐
 * @version V1.0
 * @Description TODO
 * @ClassName RunableTask
 * @date 2020/11/1 22:36
 * @Copyright © 2020 阿里巴巴
 */
public class RunableTask implements Runnable {
    private static Logger Logger = LoggerFactory.getLogger(RunableTask.class);
    private int i;
    @Override
    public void run() {
        Logger.info("{}执行了Runnable接口任务,该任务第{}次执行", Thread.currentThread().getName(), ++i);
    }
}
