package com.purefun.fstp.core.configuration;

import java.util.Map;

public class FstpConfiguration {
	/*	Java可选redis/ignit，python仅redis  	*/
	public String cacheType;
	
	/*	平台语言,目前仅支持Qpid，后续对中间件进行封装  	*/
	public String platfom;	
	
	/*	该服务可以管理的bomap。
	 *  <topic(或destination),boName>,Query和QNS时被请求时需要用到  	*/
	public Map managerBOMap;//
	
	/*	Java可选redis/ignit，python仅redis  	*/
	public FstpConfiguration() {
		
	}

	public String getCacheType() {
		return cacheType;
	}

	public void setCacheType(String cacheType) {
		this.cacheType = cacheType;
	}

	public String getPlatfom() {
		return platfom;
	}

	public void setPlatfom(String platfom) {
		this.platfom = platfom;
	}

	public Map getManagerBOMap() {
		return managerBOMap;
	}

	public void setManagerBOMap(Map managerBOMap) {
		this.managerBOMap = managerBOMap;
	}	
}
