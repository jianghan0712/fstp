package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;

@Entity  
@Table(name="TestBO") 
public class TestBO  implements BaseBO {
	private static final long serialVersionUID = 2L;
	
	@Id
	public String uuid = UUID.createUuid();					//bo实体的唯一标识，key
	
	public long boid = serialVersionUID;					//bo的唯一标识，key
	
	public int msgtype = 1;									//bo的类型（1:manger，2:trade，3:marketdata）
	
	public String destination = "pilot.core.rpc.testone";
	
	public String servername = null;
	
	public String msg = null;
	
	public String getServername() {
		return servername;
	}
	public void setServername(String servername) {
		this.servername = servername;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}
	public String toString() {
		
		return " uuid:"+ this.uuid ;	
	}



}
