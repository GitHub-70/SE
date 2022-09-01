package com.tansun.utlis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * 目标方法重试工具类
 *
 */
public class RetryUtils {

    private static final Logger logger = LoggerFactory.getLogger(RetryUtils.class);

    private RetryUtils(){}

    /**
     *
     * @param t           目标方法的入参
     * @param function    执行目标方法的入参 T ，返回类型 R
     * @param sleepTime   重试时间间隔
     * @param retryTime   重试次数
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R execute(T t, Function<T, R> function, int sleepTime, int retryTime) throws InterruptedException {
        R r = null;
        for (int i = 0; i < retryTime; i++) {
           try {
               logger.info("第{}次，执行目标方法", i+1);
               return function.apply(t);
           } catch (RuntimeException e){
               // 多久重试一次
               Thread.currentThread().sleep(sleepTime);
               logger.error("重试方法第{}次失败", i+1);
           }
        }

        if (null == r){
            try {
                throw new RuntimeException("目标方法重试失败");
            } catch (RuntimeException e){
                e.printStackTrace();
            }
        }
        return r;
    }


}
