package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public AuthenticationService (UserRepository repository) {
		this.userRepository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

}
