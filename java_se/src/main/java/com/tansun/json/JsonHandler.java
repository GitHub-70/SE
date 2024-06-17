package com.tansun.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonHandler {
    public static void main(String[] args) {
        String jsonStr = "[{\"name\":\"Alice\",\"age\":30},{\"name\":\"Bob\",\"age\":25}]";

        JSONArray jsonArray = JSON.parseArray(jsonStr);

        for (Object obj : jsonArray) {
            if (obj instanceof JSONObject) {
                JSONObject jsonObj = (JSONObject) obj;
                String name = jsonObj.getString("name");
                int age = jsonObj.getIntValue("age");
                System.out.println("Name: " + name + ", Age: " + age);
            }
        }
    }

    
}
