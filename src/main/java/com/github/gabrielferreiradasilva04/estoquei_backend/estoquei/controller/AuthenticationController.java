package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.AuthenticationDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.LoginResponseDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private TokenService tokenService;
	
	public AuthenticationController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.userRepository = repository;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationDto auth){
		var userNamePassword = new UsernamePasswordAuthenticationToken(auth.email(), auth.password());
		var authenticate = this.authenticationManager.authenticate(userNamePassword);
		var token = tokenService.generateToken((UserEntity) authenticate.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDto(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserEntity user){
		if(this.userRepository.findByEmail(user.getEmail()) != null) {
			return ResponseEntity.badRequest().build();
		}else {
			String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encryptedPassword);
			userRepository.save(user);
			return ResponseEntity.ok().build();
		}
	}
}
