package com.purefun.fstp.example.bo;

import com.purefun.fstp.core.bo.BaseBO;
import com.purefun.fstp.core.tool.UUID;

public class ExampleBO implements BaseBO  {	
	public String uuid = UUID.createUuid();					
	
	public long boid = 6L;					
		
	public String destination = "fstp.core.example.publish";
	
	public String name = null;
	
	public int age = 0;
}
