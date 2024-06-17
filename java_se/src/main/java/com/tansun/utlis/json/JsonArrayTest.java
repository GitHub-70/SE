package com.tansun.utlis.json;

import com.alibaba.fastjson.JSONArray;
//import org.json.JSONArray;
//import org.json.JSONException;
/**
 * org.json.JSONArray
 * 		--不能转换 含有0开始的数据，否则会造成错误的数据
 * @author Administrator
 *
 */
public class JsonArrayTest {

	//准备数据
	static String jsonarr = "[{pid:023,name:离散,age:20300},{pid:024,name:喜禾,age:30200}]";

	public static void main(String[] args) {
		
		jsonConvertArray();
		
	}

	private static void jsonConvertArray() {
		JSONArray jsonArray = new JSONArray();

		JSONArray objects = jsonArray.fluentAdd(jsonarr);
		System.out.println("jsonArray:--->"+objects);
	}
}
