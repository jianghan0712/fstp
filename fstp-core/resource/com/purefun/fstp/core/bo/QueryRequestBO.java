package com.purefun.fstp.core.bo;

import java.io.Serializable;

import com.purefun.fstp.core.tool.UUID;

public class QueryRequestBO implements BaseBO  {
	
	public String uuid = UUID.createUuid();					
	
	public long boid = 5L;					
		
	public String destination = "fstp.core.manager.queryrequest";
	
	public String requestServiceName = null;
	
	public String respondServiceName = null;
	
	public String querytopic = null;
	
	public String queryBoDestination = null;
	
}
