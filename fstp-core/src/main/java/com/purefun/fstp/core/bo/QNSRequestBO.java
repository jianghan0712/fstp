package com.purefun.fstp.core.bo;

import java.io.Serializable;

import com.purefun.fstp.core.tool.UUID;

public class QNSRequestBO implements BaseBO  {
	private static final long serialVersionUID = 4L;
	
	public String uuid = UUID.createUuid();					
	
	public long boid = serialVersionUID;					
		
	public String destination = "fstp.core.manager.qnsrequest";
	
	public String servername = null;	
	
	public String request = null;
	
	public QNSRequestBO(String msg){
		this.request = msg;
	}

	public String toString() {		
		return "uuid:"+this.uuid ;	
	}

	public String getUuid() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	@Override
	public String getDestination() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
