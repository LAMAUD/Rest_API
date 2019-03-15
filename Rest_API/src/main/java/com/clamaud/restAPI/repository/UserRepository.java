package com.clamaud.restAPI.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.clamaud.restAPI.domain.jpa.User;

/**
 * @author clamaud
 *
 * Repository Spring DATA
 */

public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findByLastName(String lastName);
	
	
}
