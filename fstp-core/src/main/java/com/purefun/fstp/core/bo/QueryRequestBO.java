package com.purefun.fstp.core.bo;

import java.io.Serializable;

import com.purefun.fstp.core.tool.UUID;

public class QueryRequestBO implements BaseBO  {
	private static final long serialVersionUID = 5L;
		
	public String uuid = UUID.createUuid();					
	
	public long boid = serialVersionUID;					
		
	public String destination = "fstp.core.manager.queryrequest";
	
	public String requestServiceName = null;
	
	public String respondServiceName = null;
	
	public String querytopic = null;
	
	public String queryBoDestination = null;
	
	public QueryRequestBO(String requestServiceName, String respondServiceName,String querytopic,String queryBoDestination){
		this.requestServiceName = requestServiceName;
		this.respondServiceName = respondServiceName;
		this.querytopic = querytopic;
		this.queryBoDestination = queryBoDestination;
	}
	
	@Override
	public String getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}

	public String getRequestServiceName() {
		return requestServiceName;
	}

	public void setRequestServiceName(String requestServiceName) {
		this.requestServiceName = requestServiceName;
	}

	public String getRespondServiceName() {
		return respondServiceName;
	}

	public void setRespondServiceName(String respondServiceName) {
		this.respondServiceName = respondServiceName;
	}

	public String getQueryBoDestination() {
		return queryBoDestination;
	}

	public void setQueryBoDestination(String queryBoDestination) {
		this.queryBoDestination = queryBoDestination;
	}

	@Override
	public String toString() {
		return "QueryRequestBO [uuid=" + uuid + ", boid=" + boid + ", destination=" + destination
				+ ", requestServiceName=" + requestServiceName + ", respondServiceName=" + respondServiceName + "]";
	}	
	
}
