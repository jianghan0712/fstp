package com.purefun.fstp.core.bo;

import java.io.Serializable;

import com.purefun.fstp.core.tool.UUID;
import com.purefun.fstp.core.tool.fstpbo;

@fstpbo(boid = 4L, destination = "fstp.core.manager.qnsrequest")
public class QNSRequestBO extends BaseBO  {	
	
	public String servername = "";	
	
	public String request = "";
}
