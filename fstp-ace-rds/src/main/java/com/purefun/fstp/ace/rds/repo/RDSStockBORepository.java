package com.purefun.fstp.ace.rds.repo;

import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.purefun.fstp.core.bo.RDSStockBO;

@Repository
public interface RDSStockBORepository extends CrudRepository<RDSStockBO, String> {
	
}
