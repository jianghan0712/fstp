package com.purefun.fstp.core.server;

public class PProperty {
	String serverName;
	String env;
	String instance;
	String fullName;
	String develop;
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDevelop() {
		return develop;
	}
	public void setDevelop(String develop) {
		this.develop = develop;
	}
	@Override
	public String toString() {
		return "PProperty [serverName=" + serverName + ", env=" + env + ", instance=" + instance + ", fullName="
				+ fullName + ", develop=" + develop + "]";
	}
	
}
