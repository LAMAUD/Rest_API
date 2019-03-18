package com.clamaud.restAPI.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@GetMapping("/{id}")
	public User findById(@PathVariable Long id){
		Optional<User> optionalUser = userRepository.findById(id);
		
		return optionalUser.get();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody User user) {
		
		User userBDD = userRepository.save(user);
		
		return userBDD;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		
		Optional<User> user = userRepository.findById(id);
		userRepository.delete(user.get());
		
	}
}
