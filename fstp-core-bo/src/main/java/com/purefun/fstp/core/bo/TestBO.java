package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fstp.core.tool.UUID;

@Entity  
@Table(name="TestBO") 
public class TestBO  implements BaseBO {
	@QuerySqlField(index=true)
	@Id
	public String uuid = UUID.createUuid();	
	@QuerySqlField
	public long boid = 2L;				
	@QuerySqlField
	public String destination = "fstp.core.rpc.testone";
	@QuerySqlField
	public String servername = "";
	@QuerySqlField
	public String msg = "";	
}
