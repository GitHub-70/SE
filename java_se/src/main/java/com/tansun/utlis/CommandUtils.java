package com.tansun.utlis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommandUtils.class);

    // 提供私有的构造函数
    private CommandUtils(){}

    /**
     *  开启一个新的 进程 执行命令
     * @param isStringArray 参数类型是否是 String[]
     * @param commandArray  String[]的命令
     * @param command   String的命令
     * @return  命令是否正常结束
     * @throws IOException
     * @throws InterruptedException
     */

    public static boolean run(boolean isStringArray, String[] commandArray, String command) throws IOException, InterruptedException {

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        String str = null;

        InputStream errorInputStream = null;
        BufferedReader errorReader = null;

        try {
            // 执行命令
            if (isStringArray){
                logger.info("执行的命令{}", commandArray);
                process = Runtime.getRuntime().exec(commandArray);
            } else {
                logger.info("执行的命令{}", command);
                process = Runtime.getRuntime().exec(command);
            }

            inputStream = process.getInputStream();
            // 如果读取的文件 还有数据 就继续读取
            if (inputStream.available() != 0){
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (null != (str = bufferedReader.readLine())){
                    logger.info("读取文件的数据：[{}]", str);
                }
            }

            // 获取Process 的子进程的错误流
            errorInputStream = process.getErrorStream();
            errorReader = new BufferedReader(new InputStreamReader(errorInputStream));
            while (null != (str = errorReader.readLine())){
                logger.info("output Stream:[{}]", str);
            }

            process.waitFor();
            // 返回程序是否 正常退出
            return process.exitValue() == 0;
        } finally {
            if (null != process)
                process.destroy();
            if (null != errorReader)
                errorReader.close();
            if (null != errorInputStream)
                errorInputStream.close();
            if (null != bufferedReader)
                bufferedReader.close();
            if (null != inputStream)
                inputStream.close();
        }
    }

}
