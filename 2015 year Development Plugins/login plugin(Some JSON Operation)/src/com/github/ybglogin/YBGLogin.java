package com.github.ybglogin;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Java_development
 * @version 1.0.0 登录类
 * 
 *          对传进来的信息进行判断 判断玩家是否注册过
 *          没有注册:对玩家输入的两次密码进行比较看是否相同,玩家的信息存入PlayInformation.xml文件中
 *          注册过:直接提示玩家请输入密码
 */
public class YBGLogin {

	/**
	 * 
	 * 注册:玩家的输入的信息（发送者,密码,重复密码）
	 * 
	 * @param Player
	 *            玩家信息
	 * @param password1
	 *            第一个参数密码
	 * @param password2
	 *            第二个参数密码
	 * @return
	 */
	public boolean registerAdd(Player Player, String password1, String password2) {
		Location location = Player.getLocation();
		// 判断玩家输入的两次密码是否相同
		if (password1.equals(password2)) {
			YBGJSONPlayerControl YBGPlayer = new YBGJSONPlayerControl(Player.getName(), password1,
					(float) location.getX(), (float) location.getY(), (float) location.getZ());
			YBGPlayer.addPlayerJsonData();
			//判断玩家是否可以移动
			YBGJSONPlayerControl.isPlayerMove = true;
			Player.sendMessage(ChatColor.GREEN +"注册成功");
			return true;
		}else{
			Player.sendMessage(ChatColor.RED +"两次输入的密码不相同...请重新输入");
		}
		return false;
	}

	/**
	 * 登录:玩家的输入的信息（发送者,密码）
	 * 
	 * @param Player
	 * @param password
	 * @return
	 */
	public boolean loginAdd(Player Player, String password) {
		if (password != null) {
			// 判断该玩家是否存在
			if (YBGJSONPlayerControl.isPlayerJSONData(Player.getName())) {
				//获取玩家最后一次退出的坐标
				Location location = Player.getLocation();
				YBGJSONPlayerControl ybgJSONPlayer = new YBGJSONPlayerControl(Player.getName(), password,
						(float) location.getX(), (float) location.getY(), (float) location.getZ());
				YBGPlayer ybgPlayer = ybgJSONPlayer.getPlayerInformation();
				//判断玩家的密码是否正确
				if(password.equals(ybgPlayer.getPassword())){
					location.setX(ybgPlayer.getLocationX());
					location.setY(ybgPlayer.getLocationY());
					location.setZ(ybgPlayer.getLocationZ());
					//返回上次玩家离开的位置
					YBGJSONPlayerControl.isPlayerMove = true;
					Player.teleport(location);
					Player.sendMessage(ChatColor.GREEN +"登录成功");
				}else{
					Player.sendMessage(ChatColor.RED +"密码错误(请确认密码正确或者用户名是否为你的账户)");
				}
			}else{
				Player.sendMessage(ChatColor.RED +"不存在此账户(请先注册<(￣ˇ￣)/...)");
			}

		}
		return false;
	}


}
