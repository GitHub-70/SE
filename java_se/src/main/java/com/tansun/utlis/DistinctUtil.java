package com.tansun.utlis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @ClassName DistinctUtil
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/8/16 21:38
 * @Copyright © 2020 阿里巴巴
 */
public class DistinctUtil {
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> concurrentHashMap = new ConcurrentHashMap<>();
        return t -> concurrentHashMap.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
