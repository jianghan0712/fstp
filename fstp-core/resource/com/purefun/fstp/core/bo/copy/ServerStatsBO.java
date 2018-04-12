package com.purefun.fstp.core.bo.copy;

import com.purefun.fstp.core.tool.UUID;

public class ServerStatsBO implements BaseBO  {		
	public String uuid = UUID.createUuid();					
	
	public long boid = 1L;					
		
	public String destination = "fstp.core.manager.serverstatus";
	
	public String servername = null;
	
	public int status = -1;		
}
