package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fstp.core.tool.UUID;
import com.purefun.fstp.core.tool.fstpbo;

@Entity  
@Table(name="TestBO") 
@fstpbo(boid = 2L, destination = "fstp.core.rpc.testone")
public class TestBO extends BaseBO {
	@QuerySqlField
	public String servername = "";
	@QuerySqlField
	public String msg = "";	
}
