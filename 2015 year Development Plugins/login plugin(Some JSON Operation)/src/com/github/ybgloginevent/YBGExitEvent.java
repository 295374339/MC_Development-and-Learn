package com.github.ybgloginevent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.ybglogin.YBGJSONPlayerControl;

/**
 * 对退出玩家的监视
 * 
 * @author Java_development
 *
 */
public class YBGExitEvent implements Listener {

	/**
	 * 当玩家退出时获取坐标并添加进JSON数据中
	 * 
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH)
	public void listenerPlayerExit(PlayerQuitEvent event) {
		YBGJSONPlayerControl.addPlayerExitLocation(event.getPlayer());
		Bukkit.getLogger().info("该玩家已经退出: " + event.getPlayer().getName());

	}
}
