package com.json.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;

public class JSONTest {



	@Test
	public void Test() {
		FileWriter writer = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
//			writer = new FileWriter(new File("JSon.json"));
//			bw = new BufferedWriter(writer);
			fr = new FileReader(new File("JSon.json"));
			br = new BufferedReader(fr);
			Gson gson = new Gson();
			MCPlayer mc= gson.fromJson(br, MCPlayer.class);
			System.out.println(mc);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br != null){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}
