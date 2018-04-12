package com.purefun.fstp.core.bo.copy;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;

@Entity  
@Table(name="TestBO") 
public class TestBO  implements BaseBO {
	@Id
	public String uuid = UUID.createUuid();	
	
	public long boid = 2L;				
		
	public String destination = "fstp.core.rpc.testone";
	
	public String servername = null;
	
	public String msg = null;
	
}
