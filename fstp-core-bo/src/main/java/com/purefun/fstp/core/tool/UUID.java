package com.purefun.fstp.core.tool;

public class UUID {
	public static String createUuid() {
		
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");	
	}
}
