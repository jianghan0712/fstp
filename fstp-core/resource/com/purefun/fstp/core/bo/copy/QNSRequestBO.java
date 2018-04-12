package com.purefun.fstp.core.bo.copy;

import java.io.Serializable;

import com.purefun.fstp.core.tool.UUID;

public class QNSRequestBO implements BaseBO  {	
	public String uuid = UUID.createUuid();					
	
	public long boid = 4L;					
		
	public String destination = "fstp.core.manager.qnsrequest";
	
	public String servername = null;	
	
	public String request = null;
}
