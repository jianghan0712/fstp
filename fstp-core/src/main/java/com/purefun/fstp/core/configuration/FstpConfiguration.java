package com.purefun.fstp.core.configuration;

public class FstpConfiguration {
	
	public String cacheType;//cache，Java可选redis/ignit，python仅redis
	
	public String platfom;	//平台语言,目前支持Java/Python,默认Qpid
	
	public FstpConfiguration() {
		
	}
}
