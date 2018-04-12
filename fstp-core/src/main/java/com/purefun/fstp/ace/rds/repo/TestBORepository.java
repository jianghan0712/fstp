package com.purefun.fstp.ace.rds.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.purefun.fstp.core.bo.copy.TestBO;

@Repository
public interface TestBORepository extends CrudRepository<TestBO, String> {
	
}
