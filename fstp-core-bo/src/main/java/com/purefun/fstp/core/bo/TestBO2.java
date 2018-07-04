package com.purefun.fstp.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.purefun.fstp.core.tool.UUID;
import com.purefun.fstp.core.tool.fstpbo;

@Entity
@Table(name="TestBO")
@fstpbo(boid = 3L, destination = "fstp.core.rpc.testtwo")
public class TestBO2 extends BaseBO {
	public String servername = "";
	
	public String msg = "";
}
