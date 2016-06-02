package com.github.ybglogin;

/**
 * 玩家信息
 * @author Java_development
 *
 */
public class YBGPlayer {
	private String name;//玩家名字
	private String password;//玩家密码
	private float locationX,locationY,locationZ;//玩家当前坐标
	public YBGPlayer(String name, String password, float locationX, float locationY, float locationZ) {
		super();
		this.name = name;
		this.password = password;
		this.locationX = locationX;
		this.locationY = locationY;
		this.locationZ = locationZ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public float getLocationX() {
		return locationX;
	}
	public void setLocationX(float locationX) {
		this.locationX = locationX;
	}
	public float getLocationY() {
		return locationY;
	}
	public void setLocationY(float locationY) {
		this.locationY = locationY;
	}
	public float getLocationZ() {
		return locationZ;
	}
	public void setLocationZ(float locationZ) {
		this.locationZ = locationZ;
	}
	@Override
	public String toString() {
		return "YBGPlayer [name=" + name + ", password=" + password + ", locationX=" + locationX + ", locationY="
				+ locationY + ", locationZ=" + locationZ + "]";
	}
	
}
