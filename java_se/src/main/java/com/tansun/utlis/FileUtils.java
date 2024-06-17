package com.tansun.utlis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 文件工具类
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils(){};

    /**
     * 删除指定目录下的所有文件
     * @param file
     */
    public static void deleteAllFile(File file){

        // pathname是构造方法传进来的路径
        // 重写 FileFilter 接口的 boolean accept(File pathname)方法
        File[] fileFilter = file.listFiles();
        if (fileFilter.length==0){
            // 没有文件或文件目录直接返回
            return;
        }

        File isDirectory = null;
        for (File fileNamePath : fileFilter) {
            if (fileNamePath.isDirectory()){
                isDirectory = fileNamePath;
                // 递归调用自身
                deleteAllFile(fileNamePath);
            } else {
                fileNamePath.delete();
            }

        }
        // 删除父文件目录
        if (isDirectory != null){
            isDirectory.delete();
        }
        // 删除最上层文件夹
        fileFilter[0].delete();
    }
}
