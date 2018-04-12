package com.purefun.fstp.core.bo;

import com.purefun.fstp.core.tool.UUID;

public class ServerStatsBO implements BaseBO  {
	private static final long serialVersionUID = 1L;
		
	public String uuid = UUID.createUuid();					//bo实体的唯一标识，key
	
	public long boid = serialVersionUID;					//bo的唯一标识，key
		
	public String destination = "fstp.core.manager.serverstatus";
	
	public String servername = null;
	
	public int status = -1;
	
	public ServerStatsBO(String serverName,int status){
		this.servername = serverName;
		this.status = status;
	}

	public String toString() {		
		return "uuid:"+this.uuid ;	
	}

	public int getStatus() {
		return status;
	}
	
	public String getServername() {
		return servername;
	}
		
}
