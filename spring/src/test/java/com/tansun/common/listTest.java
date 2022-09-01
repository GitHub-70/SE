package com.tansun.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class listTest {

    public static void main(String[] args) {
        linkedListToString();
//        List<Object> linkedList = new ArrayList<>();
//        linkedList.add("aa");
//        linkedList.add("bb");
//        System.out.println(linkedList.toString());

    }

    private static void linkedListToString() {
        List<Object> linkedList = new LinkedList<>();
        linkedList.add("aa");
        linkedList.add("bb");
        System.out.println(linkedList.toString());

        int i = 0;
        for (;;){
            if (i == 2){
                break;
            }
            i++;
            System.out.println(i);
        }
    }
}
