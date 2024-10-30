package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.DuplicateRecordException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.UserNotFoundException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.StockRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class StockService {
	
	private final StockRepository stockRepository;
	private final UserRepository userRepository;

	public StockService(StockRepository repository, UserRepository userRepository) {
		this.stockRepository = repository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public StockEntity save(@NotNull UUID user_id, @NotNull StockEntity stock) {
		if(user_id != null && stock != null) {
			Optional<UserEntity> userOptional = this.userRepository.findById(user_id);
			if(userOptional.isPresent()) {
				UserEntity user = userOptional.get();
				
				Set<StockEntity> userStocks = user.getStocks();
				Set<UserEntity> stockUsers = stock.getUsers();
				
				if(userStocks == null) {
					userStocks = new HashSet<StockEntity>();
				}
				if(stockUsers == null) {
					stockUsers = new HashSet<UserEntity>();
				}
				
				if(!userStocks.contains(stock)) {
					userStocks.add(stock);
					stockUsers.add(user);
					
					user.setStocks(userStocks);
					stock.setUsers(stockUsers);
					
					this.stockRepository.save(stock);
					this.userRepository.save(user);		
				}else {
					throw new DuplicateRecordException("Esse usuario ja possui esse estoque");
				}
			}else {
				throw new UserNotFoundException("Usuário não encontrado");
			}
			
			return stock;
		}
		
		
		return null;
	}
}
