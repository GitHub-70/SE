package com.tansun.gc;

public class NewObject {


    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (int j = 0; j < 1111000000; j++) {
                    byte[] bytes = new byte[2048];
                    System.out.println(i += 5);
                }
            }
        }).start();
    };
}
