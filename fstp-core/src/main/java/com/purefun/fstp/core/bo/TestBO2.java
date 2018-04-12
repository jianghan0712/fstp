package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;

@Entity
@Table(name="TestBO")
public class TestBO2 implements BaseBO {
	@Id 
	public String uuid = UUID.createUuid();			//bo实体的唯一标识，key
	
	public long boid = 3;					//bo的唯一标识，key
		
	public String destination = "fstp.core.rpc.testtwo";
	
	public String servername = null;
	
	public String msg = null;
}
