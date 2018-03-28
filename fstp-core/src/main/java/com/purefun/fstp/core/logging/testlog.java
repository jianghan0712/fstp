package com.purefun.fstp.core.logging;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class testlog {
	  
	public static void main(String[] args) {
		Logger log = PLogger.getLogger(PLogger.class);
	
		log.info("1234{}","567");
	}
}
