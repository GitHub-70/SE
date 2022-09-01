package com.tansun.language;


import java.text.MessageFormat;

/**
 * https://blog.csdn.net/xiaokui_wingfly/article/details/46124057
 *
 * MessageFormat模式（主要部分）：
 *
 * FormatElement:
 *          { ArgumentIndex }：是从0开始的入参位置索引。
 *          { ArgumentIndex , FormatType }
 *          { ArgumentIndex , FormatType , FormatStyle }
 *
 *
 *  FormatType: ：指定使用不同的Format子类对入参进行格式化处理。值范围如下：
 *          number：调用NumberFormat进行格式化
 *
 *          date：调用DateFormat进行格式化
 *
 *          time：调用DateFormat进行格式化
 *
 *          choice：调用ChoiceFormat进行格式化
 *
 *
 *  FormatStyle:：设置FormatType中使用的格式化样式。值范围如下：
 *          short
 *          medium
 *          long
 *          full
 *          integer
 *          currency
 *          percent
 *          SubformatPattern (子格式模式，形如#.##)
 *
 * 1、{0}和{1,number,short}和{2,number,#.#};都属于FormatElement，0,1,2是ArgumentIndex。
 *
 * 2、{1,number,short}里面的number属于FormatType，short则属于FormatStyle。
 *
 * 3、{1,number,#.#}里面的#.#就属于子格式模式。
 */
public class TemplateFormat {
    public static void main(String[] args) {
//        getStringTemplate();
//        getStringTemplatePig1();
//        getStringTemplatePig2();
        getStringTemplatePig3();

    }

    private static void getStringTemplate(){
        String mst = "{0}{1}{2}{3}{3}";
        String mst2 = "{4}";
        String [] array = new String[]{"A","B","C","D","E"};
        // format底层是 new MessageFormat，所以当多次用到同一个模板时，
        // 可以先new MessageFormat对象来复用
        String value = MessageFormat.format(mst, array);
        String value2 = MessageFormat.format(mst2, array);
        System.out.println(value);
        System.out.println(value2);
    }

    /**
     * 想输出有单引号  单个单引号 会失效
     *                两个单引号 才会成功
     *
     * 双引号 则需要转义
     *
     * 单引号会使其后面的占位符均失效
     */
    private static void getStringTemplatePig1(){
        String mst1 = "oh,{0} is a pig";
        String mst2 = "oh,{0} is a 'pig'";
        String mst3 = "oh,{0} is a ''pig''";
        String mst4 = "oh,{0} is a \"pig\"";
        String mst5 = "oh,'{0} is a pig";
        String name = "zhangsan";
        // format底层是 new MessageFormat，所以当多次用到同一个模板时，
        // 可以先new MessageFormat对象来复用
        String value1 = MessageFormat.format(mst1, name);
        String value2 = MessageFormat.format(mst2, name);
        String value3 = MessageFormat.format(mst3, name);
        String value4 = MessageFormat.format(mst4, name);
        String value5 = MessageFormat.format(mst5, name);
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
        System.out.println(value4);
        System.out.println(value5);
    }

    /**
     * 使用子格式模式
     */
    private static void getStringTemplatePig2(){
        String mst1 = "oh,{0,number,#.#} is a good number";
        String mst2 = "oh,{0,number,#.##} is a good number";
        Double aDouble = Double.valueOf("3.1415");
        System.out.println(aDouble);
        // format底层是 new MessageFormat，所以当多次用到同一个模板时，
        // 可以先new MessageFormat对象来复用
        String value1 = MessageFormat.format(mst1, aDouble);
        String value2 = MessageFormat.format(mst2, aDouble);
        System.out.println(value1);
        System.out.println(value2);
    }

    /**
     * 使用左{ 需加单引号
     *  其他则不需要
     */
    private static void getStringTemplatePig3(){
        String mst1 = "oh,'{'{0}} is a pig";
        String mst2 = "oh,[{0}] is a pig";
        String name = "zhangsan";
        // format底层是 new MessageFormat，所以当多次用到同一个模板时，
        // 可以先new MessageFormat对象来复用
        String value1 = MessageFormat.format(mst1, name);
        String value2 = MessageFormat.format(mst2, name);
        System.out.println(value1);
        System.out.println(value2);
    }

}
