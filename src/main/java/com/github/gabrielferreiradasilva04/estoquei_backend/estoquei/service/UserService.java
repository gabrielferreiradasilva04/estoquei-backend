package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.EntityNotFoundException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserEntity update(UUID id, UserEntity user) throws EntityNotFoundException {
		UserEntity userFind = this.findById(id);
		if(userFind != null) {
			userFind.setNickname(user.getNickname());
			userFind.setFirstName(user.getFirstName());
			userFind.setLastName(user.getLastName());
			userFind.setPhoneNumber(user.getPhoneNumber());
			userFind.setActive(user.getActive());
			
			return this.userRepository.save(userFind);
		}
		throw new EntityNotFoundException("Usuário não encontrado");
	}

	public UserEntity findById(UUID id) {
		var optional = this.userRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}
	
	public List<StockEntity> findUserStocks(UUID userId){
		return this.userRepository.findUserStocks(userId);
	}



}
