package com.github.ybglogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.github.ybgTools.Tools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class YBGJSONPlayerControl {
	// 存贮JSON文件
	private static File MCPlayerData = new File("plugins/MCPlayerData.json");
	// 出生点设置
	public static World world = null;

	private String name;
	private String password;
	private float locationX;
	private float locationY;
	private float locationZ;

	// 是否同意玩家移动
	public static boolean isPlayerMove = false;

	// 出生点的修改
	/**
	 * 构造时添加玩家的信息
	 * 
	 * @param name
	 * @param password
	 * @param locationX
	 * @param locationY
	 * @param locationZ
	 */
	public YBGJSONPlayerControl(String name, String password, float locationX, float locationY, float locationZ) {
		super();
		this.name = name;
		this.password = password;
		this.locationX = locationX;
		this.locationY = locationY;
		this.locationZ = locationZ;
	}

	/**
	 * 确认没有该文件后进行文件创建
	 */
	public void createFile() {
		BufferedWriter bw = null;
		try {
			bw = Tools.createBufferedWriter(MCPlayerData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Tools.closeIO(null, bw);
		}
	}

	/**
	 * 添加玩家数据方法
	 * 
	 * @param name
	 *            名字
	 * @param password
	 *            密码
	 * @param locationX
	 *            玩家X坐标
	 * @param locationY
	 *            玩家Y坐标
	 * @param locationZ
	 *            玩家Z坐标
	 */
	public boolean addPlayerJsonData() {
		/*
		 * 1.  判断该玩家是否已注册 首先将数据单元格式化 2.读取先前的数据并将新的数据添加到数组内 3.将文件输出
		 */
		if (name != null && password != null) {
			/*
			 * 1.
			 */
			//如果是已注册玩家无需再进行注册
			if(isPlayerJSONData(name)){
				return false;
			}
			// 用于存入玩家数据的数组
			JsonArray jsonArray = new JsonArray();
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("name", name);
			jsonObject.addProperty("password", password);
			jsonObject.addProperty("locationX", locationX);
			jsonObject.addProperty("locationY", locationY);
			jsonObject.addProperty("locationZ", locationZ);
			// 将上面的属性封装成元素
			JsonElement elementPlayer = jsonObject;
			/*
			 * 2 .
			 */

			BufferedWriter bw = null;
			BufferedReader br = null;
			try {
				// 判断如果没有该文件则创建该文件
				if (!MCPlayerData.exists()) {
					createFile();
				}
				br = Tools.createBufferedReader(MCPlayerData);
				/*
				 * 解析这段读取的JSON数据 判断文件中是否有数据
				 */
				if (MCPlayerData.length() != 0) {
					JsonParser jsonParser = new JsonParser();
					JsonElement jsonElement = jsonParser.parse(br);
					jsonArray = jsonElement.getAsJsonArray();
					jsonArray.add(elementPlayer);
				} else {
					// 即使原先没有数据也要加上玩家的信息
					jsonArray.add(elementPlayer);
				}

				/*
				 * 3.
				 */
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				bw = Tools.createBufferedWriter(MCPlayerData);
				// 最后直接输出成文件就行了
				bw.write(gson.toJson(jsonArray));
				bw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Tools.closeIO(br, bw);
			}
		}
		return true;
	}

	/**
	 * 将该玩家最后退出的地址 存入JSON数据中
	 * 
	 * @param player
	 */
	public static void addPlayerExitLocation(Player player) {
		/*
		 * 1.读取文件中的数据 2.根据玩家名找到他的JSON数据 3.最后修改
		 */
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = Tools.createBufferedReader(MCPlayerData);
			// 1
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(br);
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			// 2
			/**
			 * 1.获取到所有数据因为删除我没想到别的方法 所以我打算使用直接删除然后加入新的数据 虽然很麻烦但是的确能达到修改玩家原先坐标的功能
			 */
			JsonArray aJsonReplace = new JsonArray();
			for(int i = 0;i < jsonArray.size(); i++){
				YBGPlayer ybgPlayer = gson.fromJson(jsonArray.get(i), YBGPlayer.class);
				if (player.getName().equals(ybgPlayer.getName())) {
					JsonObject jsonObject = new JsonObject();
					jsonObject.addProperty("name", ybgPlayer.getName());
					jsonObject.addProperty("password", ybgPlayer.getPassword());
					Location location = player.getLocation();
					jsonObject.addProperty("locationX", (float) location.getX());
					jsonObject.addProperty("locationY", (float) location.getY());
					jsonObject.addProperty("locationZ", (float) location.getZ());
					JsonElement eJsonReplace = jsonObject;
					aJsonReplace.add(eJsonReplace);
					continue;
				}
				aJsonReplace.add(jsonArray.get(i));
			}
			// 3
			bw = Tools.createBufferedWriter(MCPlayerData);
			bw.write(gson.toJson(aJsonReplace));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Tools.closeIO(br, bw);
		}
	}

	/**
	 * 通过玩家名判断该玩家是否在该数据中 如果存在那则为登录账号,不存在及为注册账号
	 * 
	 * @param playerName
	 *            需要判断的玩家名
	 * @return false 未注册 true 登录玩家
	 */
	public static boolean isPlayerJSONData(String playerName) {
		/**
		 * 步骤 1.先判断数据文件是否存在 2.获取文件数据转成我们所需要使用的玩家名 3.进行玩家名的判断
		 */
		/*
		 * 判断此文件是否存在不存在则证明无数据无玩家故返回false 让玩家先进行注册
		 */
		// 1
		if (!MCPlayerData.exists()) {
			return false;
		}
		// 2
		BufferedReader br = null;
		try {
			br = Tools.createBufferedReader(MCPlayerData);
			/*
			 * 必要的解析数据
			 */
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(br);
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			Iterator<JsonElement> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				YBGPlayer player = gson.fromJson(iterator.next(), YBGPlayer.class);
				// 3
				if (player.getName().equals(playerName)) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Tools.closeIO(br, null);
		}
		return false;
	}

	/**
	 * 1.通过名字进行对改玩家查找 2.返回这个玩家的参数所对应的实例
	 * 
	 * 前提是先要判断该玩家是否存在
	 * 
	 * @param name
	 * @return
	 */
	public YBGPlayer getPlayerInformation() {
		/*
		 * 1.读取JSON文件获取我们所要的玩家的信息
		 */
		BufferedReader br = null;
		try {
			br = Tools.createBufferedReader(MCPlayerData);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(br);
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			Iterator<JsonElement> iterator = jsonArray.iterator();
			/*
			 * 判断与输入玩家的名字相同的玩家 如果该玩家名相同则返回该玩家实例
			 */
			while (iterator.hasNext()) {
				YBGPlayer ybgPlayer = gson.fromJson(iterator.next(), YBGPlayer.class);
				if (name.trim().equals(ybgPlayer.getName())) {
					return ybgPlayer;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Tools.closeIO(br, null);
		}

		return null;
	}

	/**
	 * 设置出生点
	 * 
	 * @param player
	 * @param X
	 * @param Y
	 * @param Z
	 */
	public static void setSpawnPlayer(Player player, double X, double Y, double Z) {
		world = player.getWorld();
		world.setSpawnLocation((int) X, (int) Y, (int) Z);
		player.sendMessage("出生点改变成功");
	}

	/*
	 * 测试某些基于文件判断的方法
	 */
	
	public static void main(String[] args) {
		// 加入一条玩家信息
		YBGJSONPlayerControl jsonPlayer = new YBGJSONPlayerControl("fff", "464dfsdfa", (float) 52.0, (float) 24.2,
				(float) 45.0);
		System.out.println(jsonPlayer.addPlayerJsonData());
	}
}
