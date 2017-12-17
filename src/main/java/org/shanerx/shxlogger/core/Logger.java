package org.shanerx.shxlogger.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {
	
	protected String		name;
	protected PrintWriter	print;
	protected File			logFile;
	protected LogLevel		defaultLevel;
	protected boolean		logToFile;
	
	protected Logger(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Logger name cannot be null!");
		}
		this.name = name;
		defaultLevel = LogLevel.GENERAL;
	}
	
	protected Logger() {
		defaultLevel = LogLevel.GENERAL;
	}
	
	public static Logger getLogger() {
		return new Logger();
	}
	
	public static Logger getLogger(String name) {
		return new Logger(name);
	}
	
	public void addLogFile(File log) {
		logFile = log;
	}
	
	public void removeLogFile() {
		logFile = null;
		print = null;
		logToFile = false;
	}
	
	public void setLogToFile(boolean logToFile) throws FileNotFoundException {
		this.logToFile = logToFile;
		print.close();
		if (logToFile) print = new PrintWriter(logFile);
	}
	
	public File getFileLog() {
		return logFile;
	}
	
	public void log(Object info) {
		log(defaultLevel, info);
	}
	
	@SuppressWarnings("deprecation")
	public void log(LogLevel level, Object info) {
		Date date = new Date();
		String text = null;
		Exception e = null;
		if (info instanceof String) {
			text = (String) info;
		}
		else{
			if (info instanceof Exception) {
				e = ((Exception)info);
			}
			else {
				text = info.toString();
			}
		}
		if (e != null) {
			System.out.print("[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "]   [" + level.getLevelName() + "]   " + (name == null ? "" : "[" + name + "]   "));
			e.printStackTrace();
			return;
		}
		System.out.println("[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "]   [" + level.getLevelName() + "]   " + (name == null ? "" : "[" + name + "]   ") + text);
	}
	
	public void logGlobal(Object info) {
		logGlobal(defaultLevel, info);
	}
	
	public void logGlobal(LogLevel level, Object info) {
		Date date = new Date();
		String text = null;
		Exception e = null;
		if (info instanceof String) {
			text = (String) info;
		}
		else{
			if (info instanceof Exception) {
				e = ((Exception)info);
			}
			else {
				text = info.toString();
			}
		}
		if (e != null) {
			System.out.print("[" + date.toString() + "]   [" + level.getLevelName() + "]   " + (name == null ? "" : "[" + name + "]   "));
			e.printStackTrace();
			return;
		}
		System.out.println("[" + date.toString() + "]   [" + level.getLevelName() + "]   " + (name == null ? "" : "[" + name + "]   ") + text);
	}
	
	public LogResult logToFile(Object info) {
		return logToFile(LogLevel.GENERAL, info);
	}
	
	@SuppressWarnings("deprecation")
	public LogResult logToFile(LogLevel level, Object info) {
		if (!logToFile) {
			return LogResult.FAILED;
		}
		Date date = new Date();
		String text = null;
		Exception e = null;
		if (info instanceof String) {
			text = (String) info;
		}
		else{
			if (info instanceof Exception) {
				e = ((Exception)info);
			}
			else {
				text = info.toString();
			}
		}
		if (e != null) {
			print.write("[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "]   [" + level.getLevelName() + "]   " + (name == null ? "" : "[" + name + "]   "));
			e.printStackTrace(print);
			print.write("\n");
			print.flush();
			return LogResult.SUCCESS;
		}
		print.write("[" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "]   [" + level.getLevelName() + "]   " + (name == null ? "" : "[" + name + "]   ") + text + "\n");
		print.flush();
		return LogResult.SUCCESS;
	}
	
	public LogResult logToFileGlobal(Object info) {
		return logToFileGlobal(LogLevel.GENERAL, info);
	}

	public LogResult logToFileGlobal(LogLevel level, Object info) {
		if (!logToFile) {
			return LogResult.FAILED;
		}
		Date date = new Date();
		String text = null;
		Exception e = null;
		if (info instanceof String) {
			text = (String) info;
		}
		else{
			if (info instanceof Exception) {
				e = ((Exception)info);
			}
			else {
				text = info.toString();
			}
		}
		if (e != null) {
			print.write("[" + date.toString() + "]   [" + level.getLevelName() + "]   " + (name == null ? "" : "[" + name + "]   "));
			e.printStackTrace(print);
			print.write("\n");
			print.flush();
			return LogResult.SUCCESS;
		}
		print.write("[" + date.toString() + "]   [" + level.getLevelName() + "]   " + (name == null ? "" : "[" + name + "]   ") + text + "\n");
		print.flush();
		return LogResult.SUCCESS;
	}

}