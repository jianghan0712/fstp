package com.purefun.fstp.core.python;

import org.slf4j.Logger;

import com.purefun.fstp.core.logging.PLogger;

public class TestPython {
	int a;
	String b;
	public static Logger log = PLogger.getLogger(TestPython.class);
	
	
	public TestPython(int a,String b) {
		this.a=a;
		this.b=b;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		TestPython.log = log;
	}
	
	
}
