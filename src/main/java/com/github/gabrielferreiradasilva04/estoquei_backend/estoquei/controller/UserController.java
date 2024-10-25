package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserRepository userRepository;
	
	public UserController(UserRepository repository) {
		this.userRepository = repository;
	}
	
	@PostMapping
	public UserEntity save(@RequestBody UserEntity user) {
		userRepository.save(user);

		return user;
	}
	
	
	
}
