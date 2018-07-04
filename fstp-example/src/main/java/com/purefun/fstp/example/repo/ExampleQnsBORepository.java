package com.purefun.fstp.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.purefun.fstp.example.bo.ExampleBO;
import com.purefun.fstp.example.bo.ExampleQnsBO;

@Repository
public interface ExampleQnsBORepository extends CrudRepository<ExampleQnsBO, String> {
	
}
