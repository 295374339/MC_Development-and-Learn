package com.github.ybgTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Tools {
	
	/**
	 * 测试JSON数据是否正常
	 */
	public static void TestJson(Player player){
		//测试
		BufferedReader br = null;
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			br = Tools.createBufferedReader(new File("plugins/MCPlayerData.json"));
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(br);
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			player.sendMessage(gson.toJson(jsonArray.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeIO(br, null);
		}
	}
	
	/**
	 * 创建输入出缓冲流
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static BufferedWriter createBufferedWriter(File file) throws Exception{
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		return bw;
	}
	
	/**
	 * 创建输入缓冲流
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static BufferedReader createBufferedReader(File file) throws Exception{
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		return br;
	}
	
	/**
	 * 关闭流的方法
	 * 
	 * @param br
	 * @param bw
	 */
	public static void closeIO(BufferedReader br, BufferedWriter bw) {
		try {
			if (br != null) {
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (bw != null) {
				bw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
