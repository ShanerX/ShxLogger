package org.shanerx.shxlogger.core;

public class test {
	
	public static void main(String...args) {
		Logger l = Logger.getLogger("Hello World");
		l.log("Hello");
		l.log(LogLevel.SEVERE, 3);
		l.logGlobal(LogLevel.WARN, "test");
		l.logGlobal("test2");
		l.log(LogLevel.FATAL, new RuntimeException());
	}

}
