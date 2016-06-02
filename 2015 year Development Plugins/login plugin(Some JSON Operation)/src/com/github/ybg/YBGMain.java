package com.github.ybg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ybglogin.YBGJSONPlayerControl;
import com.github.ybglogin.YBGLogin;
import com.github.ybgloginevent.YBGExitEvent;
import com.github.ybgloginevent.YBGLoginEvent;

/**
 * 主插件运行区...
 * @author EneJavSen
 * @version 1.0.0
 */
public class YBGMain extends JavaPlugin {

	private YBGLoginEvent loginEvent = new YBGLoginEvent();
	private YBGExitEvent exitEvent = new YBGExitEvent();
	
	private YBGLogin loginDemo = new YBGLogin();

	/**
	 * 插件初始化
	 */
	@Override
	public void onLoad() {
		super.onLoad();
		getLogger().info("=====YBG社插件初始化中...稍等");
	}

	/**
	 * 插件启动完成
	 */
	@Override
	public void onEnable() {
		super.onEnable();
		// 注册监听器
		getServer().getPluginManager().registerEvents(loginEvent, this);
		getServer().getPluginManager().registerEvents(exitEvent, this);
		getLogger().info("=====YBG社插件启动成功");
		getLogger().info("=====建筑破坏提示插件已启动");
	}

	/**
	 * 插件卸载
	 */
	@Override
	public void onDisable() {
		super.onDisable();
		getLogger().info("=====YBG社插件已退出...欢迎使用");
	}

	/**
	 * 对发送者的命令进行判断
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		// 判断发送方是否为玩家
		if (sender instanceof Player) {
			// 判断输入的是否为注册命令
			if ("register".equalsIgnoreCase(command.getName())) {
				// 判断参数是否为2个参数
				if (args.length == 2) {
					// 玩家注册
					loginDemo.registerAdd((Player) sender, args[0], args[1]);
				} else {
					return false;
				}
				
				//判断输入的是否为Login命令
			}else if ("login".equalsIgnoreCase(command.getName())) {
				//判断参数是否为1个参数
				if (args.length == 1) {
					//玩家的登录
					loginDemo.loginAdd((Player)sender, args[0]);
				}else{
					return false;
				}
				
				//设置玩家出生点
			}else if("setspawn".equalsIgnoreCase(command.getName())){
				if (args.length == 3) {
					//设置为需要使用的坐标点
					YBGJSONPlayerControl.setSpawnPlayer((Player)sender, Double.parseDouble(args[0]),
							Double.parseDouble(args[1]), Double.parseDouble(args[2]));
				}
			}else{
				return false;
			}
		} else {
			sender.sendMessage("服务器方不可以发送此命令");
		}
		return false;
	}
}
