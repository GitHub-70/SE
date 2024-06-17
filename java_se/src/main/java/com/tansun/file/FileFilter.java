package com.tansun.file;

import java.io.File;

/**
 * @ClassName FileFilter
 * @Description TODO
 * @author 吴槐
 * @version V1.0
 * @date 2022/7/5 14:51
 * @Copyright © 2020 阿里巴巴
 */
public class FileFilter {

    public static void main(String[] args) {


        // File类遍历(文件夹)目录功能
        File file = new File("E:\\file");
        String[] list = file.list();
        File[] files = file.listFiles();
        for (String fileName : list) {
            System.out.println(fileName);
        }
        System.out.println("------------------分割符-----------------");
        for (File getFile : files) {
            System.out.println(getFile);
        }
        System.out.println("------------------分割符-----------------");

        // 通过文件过滤器，来获取指定的所有文件
        getAllFile(file);

        // 递归删除文件及目录
        File file1 = new File("E:\\file\\testdelete");
        deleteAllFile(file1);
    }
    
    /* *
     * @Author 吴槐
     * @Description 获取以 ".java" 结尾的所有文件
     * @Date 14:57 2022/7/5
     * @Param null
     * @return 
     *       
     **/
    private static void getAllFile(File file){

        // pathname是构造方法传进来的路径
        // 重写 FileFilter 接口的 boolean accept(File pathname)方法
        File[] fileFilter = file.listFiles(pathname -> pathname.isDirectory() ||
                pathname.getName().toLowerCase().endsWith(".java"));

        for (File fileNamePath : fileFilter) {
            if (fileNamePath.isDirectory()){

                // 递归调用自身
                getAllFile(fileNamePath);
            } else {
                System.out.println(fileNamePath);
            }
            
        }
        
    }

    /**
     * 递归删除文件、文件目录
     * @param file
     */

    private static void deleteAllFile(File file){

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
