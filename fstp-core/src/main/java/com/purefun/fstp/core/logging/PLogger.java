package com.purefun.fstp.core.logging;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class PLogger{
	private Logger logger;
	
	public PLogger(Class server) {
	}
	
	public static Logger getLogger(Class classz) {
		String className = classz.getName();
		className = className.substring(className.lastIndexOf(".")+1);
		
		Properties pro=new Properties();
		pro.put("log4j.rootLogger", "info,stdout,A92");
		
		pro.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
		pro.put("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
		pro.put("log4j.appender.stdout.layout.ConversionPattern", "[%-5p] [%d{yyyy-MM-dd HH:mm:ss.SSS}] [%c]  %m%n");
		pro.put("log4j.additivity.stdout", "false");

		pro.put("log4j.logger.common", "INFO,stdout,A92");
		pro.put("log4j.additivity.common", "false");
		pro.put("log4j.appender.A92", "org.apache.log4j.DailyRollingFileAppender");
		pro.put("log4j.appender.A92.DatePattern", "'.'yyyy-MM-dd-HH");
		pro.put("log4j.appender.A92.layout", "org.apache.log4j.PatternLayout");
		pro.put("log4j.appender.A92.layout.ConversionPattern", "[%-5p] [%d{yyyy-MM-dd HH:mm:ss.SSS}] [%c]  %m%n");
		pro.put("log4j.appender.A92.file", "./logging/" + className + ".log");
		PropertyConfigurator.configure(pro);
		
		return LoggerFactory.getLogger(classz);
	}
	public org.slf4j.Logger getLogger() {
		return logger;
	}
	
}
