package org.shanerx.shxlogger.core;

public enum LogLevel {
	
	GENERAL("GENERAL", (byte) 0),
	INFO("INFO", (byte) 1),
	WARN("WARN", (byte) 2),
	SEVERE("WARN", (byte) 3),
	FATAL("FATAL", (byte) 4);
	
	
	private String levelName;
	private byte level;
	
	LogLevel(String name, byte level) {
		this.levelName = name;
		this.level = level;
	}
	
	public String getLevelName() {
		return levelName;
	}
	
	public byte getLevel() {
		return level;
	}

}