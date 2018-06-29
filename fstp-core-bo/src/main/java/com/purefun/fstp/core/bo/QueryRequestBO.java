package com.purefun.fstp.core.bo;

import java.io.Serializable;

import com.purefun.fstp.core.tool.UUID;

public class QueryRequestBO implements BaseBO  {
	
	public String uuid = UUID.createUuid();					
	
	public long boid = 5L;					
		
	public String destination = "fstp.core.manager.queryrequest";
	
	public String requestServiceName = "";
	
	public String respondServiceName = "";
	
	public String querytopic = "";
	
	public String tempTopic = "";	
}
