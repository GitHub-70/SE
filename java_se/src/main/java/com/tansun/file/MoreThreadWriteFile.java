package com.tansun.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoreThreadWriteFile {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws IOException, InterruptedException {
//        String partFileNamePart = "E:\\file\\testdelete\\cfmfile-%s.txt";
        String fileName = "E:\\file\\testdelete\\cfmfile.txt";
        String content = "9B31022A|00|6214286666008621513|20231021|20605|NY|1|51462249986113856|20231021020605002205|20230646621486666008621513|20231021020605002207|100415|9B31022A|00|6214286666008621513|20231021|20605|NY|1|51462249986113856|20231021020605002205|20230646621486666008621513|20231021020605002207|100415|\r\n";
        File file = new File(fileName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()){
            System.out.println("创建文件："+fileName);
            file.createNewFile();
        }
        Path path = Paths.get(fileName);
        byte[] fileInfo = content.getBytes(StandardCharsets.UTF_8);

        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
//            String reFileName = String.format(partFileNamePart, i);
//            File file1 = new File(reFileName);
//            if (!file1.exists()){
//                System.out.println("创建文件："+file1.getAbsolutePath());
//                file1.createNewFile();
//            }
//            Path path = Paths.get(file1.getAbsolutePath());

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 2500000; j++) {
                            Files.write(path, fileInfo, StandardOpenOption.APPEND);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("执行结束");
    }
}
