package com.purefun.fstp.core.model.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.purefun.fstp.core.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
	
}
