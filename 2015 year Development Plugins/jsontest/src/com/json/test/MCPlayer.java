package com.json.test;

public class MCPlayer {
	private String playerName;
	private String playerPassword;
	private float x,y,z;
	
	public MCPlayer(String playerName, String playerPassword, float x, float y, float z) {
		super();
		this.playerName = playerName;
		this.playerPassword = playerPassword;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerPassword() {
		return playerPassword;
	}
	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	@Override
	public String toString() {
		return "MCPlayer [playerName=" + playerName + ", playerPassword=" + playerPassword + ", x=" + x + ", y=" + y
				+ ", z=" + z + "]";
	}
	
	
}
