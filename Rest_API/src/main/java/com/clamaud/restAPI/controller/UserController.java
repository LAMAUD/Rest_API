package com.clamaud.restAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clamaud.restAPI.domain.jpa.User;
import com.clamaud.restAPI.repository.UserRepository;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	
	@GetMapping("/lastname/{userLastName}")
	public List<User> findByLastName(@PathVariable String userLastName){
		return userRepository.findByLastName(userLastName);
	}
}
