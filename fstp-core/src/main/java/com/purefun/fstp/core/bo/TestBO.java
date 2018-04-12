package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;

@Entity  
@Table(name="TestBO") 
public class TestBO  implements BaseBO {
	@Id
	private String uuid = UUID.createUuid();					//bo实体的唯一标识，key
	
	public long boid = 2L;					//bo的唯一标识，key
		
	public String destination = "fstp.core.rpc.testone";
	
	public String servername = null;
	
	public String msg = null;
	
}
