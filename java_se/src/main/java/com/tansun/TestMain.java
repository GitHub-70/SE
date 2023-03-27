package com.tansun;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        boolean result = true;

        List<Boolean> booleans = new ArrayList<>();
        booleans.add(isResult(result));
        // result传入到方法isResult中，并且返回，在上层方法是不可见了，所以还是true
        System.out.println("result:"+result);
        for (Boolean aBoolean : booleans) {
            System.out.println("aBoolean:"+aBoolean);
            if (!aBoolean){
                result = aBoolean;
            }
        }
    }

    private static boolean isResult(boolean result) {
        result = false;
        return result;
    }
}
