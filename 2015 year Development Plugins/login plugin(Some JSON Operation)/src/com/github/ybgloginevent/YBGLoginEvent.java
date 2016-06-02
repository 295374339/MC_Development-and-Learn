package com.github.ybgloginevent;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.ybglogin.YBGJSONPlayerControl;

/**
 * 对登陆玩家进行监听
 * 
 * @author Java_development
 */
public class YBGLoginEvent implements Listener {
	private static String LOGIN_INFORMATION = ChatColor.YELLOW + "-----★★★欢迎登陆MC_Java ♂ 家服务器(￣︶￣)↗★★★-----\n";
	private static String LOGIN_CMD = ChatColor.YELLOW + "-----该用户名已经注册请输入登录密码\n-----输入格式为: /login <密码>";
	private static String REGISTER_CMD = ChatColor.YELLOW + "-----该用户名未被注册请输入注册信息\n-----输入格式为:/register <密码> <确认密码>";

	/**
	 * 用于提示玩家的信息 让玩家进行注册或者是登录才可以移动
	 * 
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH)
	public void listenerPlayerLogin(PlayerJoinEvent event) {

		// 对刚进入的玩家发送欢迎信息
		final Player player = event.getPlayer();
		player.sendMessage(LOGIN_INFORMATION);
		// 让该玩家处于出生地点无法移动只有当他注册完了才可以移动
		YBGJSONPlayerControl.isPlayerMove = false;
		final Location location = event.getPlayer().getLocation();
		final World world = location.getWorld();

		// 通过不断传送到出生点防止玩家移动
		Thread thread = new Thread() {
			@Override
			public void run() {
				super.run();
				while (!YBGJSONPlayerControl.isPlayerMove) {
					try {
						/*
						 * 判断如果没有设置过出生点的位置则使用默认的出生点 否则就判断使用设置过的出生点
						 */
						if (YBGJSONPlayerControl.world != null) {
							player.teleport(YBGJSONPlayerControl.world.getSpawnLocation());
						} else {
							Location locationSpawn = world.getSpawnLocation();
							player.teleport(locationSpawn);
						}
						Thread.currentThread();
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
		// 对玩家是否是该服务器已经注册的玩家进行判断
		if (YBGJSONPlayerControl.isPlayerJSONData(player.getName())) {
			player.sendMessage(LOGIN_CMD);
		} else {
			player.sendMessage(REGISTER_CMD);
		}
	}

}
