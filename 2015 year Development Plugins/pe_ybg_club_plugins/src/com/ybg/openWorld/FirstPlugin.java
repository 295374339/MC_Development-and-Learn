package com.ybg.openWorld;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;

public class FirstPlugin extends PluginBase{

	/**
	 * 初始化插件
	 */
	public void onLoad(){
		super.onLoad();
		getLogger().warning("=======YBG插件初始化中....");
	}

	/**
	 * 插件运行
	 */
	@Override
	public void onEnable() {
		super.onEnable();
		getLogger().warning("=======欢迎使用YBG社插件");
	}
	
	/**
	 * 重写命令函数
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		switch(command.getName()){
		//这里指的是命令的名字
		case "helloworld":
			sender.sendMessage("调用ybg插件的helloWorld!");
			return true;
		default:
			return false;
		}
	}
	/**
	 * 插件结束
	 */
	@Override
	public void onDisable() {
		super.onDisable();
		getLogger().warning("=======感谢使用YBG插件..插件已结束");
	}
}
