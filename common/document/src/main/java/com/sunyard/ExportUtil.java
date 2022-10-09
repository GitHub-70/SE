package com.sunyard;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

public class ExportUtil {

    // 显示导出表的标题
    private String title;
    // 导出表的列名
    private String[] rowName;
    // 表数据内容
    private List<Object[]> dataList;

    public static <T> void listToExcel(List<T> list,
                                       LinkedHashMap<String, String> linkedHashMap,
                                       String sheetName, int sheetSize,
                                       OutputStream outputStream){
        if (null == list || list.size() == 0){
            throw new RuntimeException("数据源 中没有数据");
        }
    }



}
