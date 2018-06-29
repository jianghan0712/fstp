package com.purefun.fstp.ace.rds.server;

import org.springframework.data.repository.CrudRepository;

public class RDSCommon {
	String tableName = null;
	String rdsCrud = null;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRdsCrud() {
		return rdsCrud;
	}

	public void setRdsCrud(String rdsCrud) {
		this.rdsCrud = rdsCrud;
	}
	
}
