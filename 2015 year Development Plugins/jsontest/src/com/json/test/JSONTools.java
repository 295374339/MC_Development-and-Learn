package com.json.test;

import org.json.JSONObject;

public class JSONTools {
	
	
	/**
	 * 得到一个JSON数据条
	 * @param key
	 * @param value
	 * @return
	 */
	public static String createJSONData(String key,String value){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		String data = jsonObject.toString();
		return data;
	}
}
