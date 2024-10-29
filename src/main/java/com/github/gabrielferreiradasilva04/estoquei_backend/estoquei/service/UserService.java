package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.UserNotFoundException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserEntity update(UUID id, UserEntity user) throws UserNotFoundException {
		UserEntity userFind = this.findById(id);
		if(userFind != null) {
			userFind.setNickname(user.getNickname());
			userFind.setFirstName(user.getFirstName());
			userFind.setLastName(user.getLastName());
			userFind.setPhoneNumber(user.getPhoneNumber());
			userFind.setActive(user.getActive());
			
			return this.userRepository.save(userFind);
		}
		throw new UserNotFoundException("Usuário não encontrado");
	}

	public UserEntity findById(UUID id) {
		var optional = this.userRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

}
