package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.SaveStockMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.SaveStockResponseMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveStockDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveStockResponseDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.DuplicateRecordException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.EntityNotFoundException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.StockRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class StockService {

	private final StockRepository stockRepository;
	private final UserRepository userRepository;
	private final SaveStockMapper defaultMapper;
	private final SaveStockResponseMapper responseMapper;

	public StockService(StockRepository repository, UserRepository userRepository, SaveStockMapper defaultMapper, SaveStockResponseMapper responseMapper) {
		this.stockRepository = repository;
		this.userRepository = userRepository;
		this.defaultMapper = defaultMapper;
		this.responseMapper = responseMapper;
	}

	@Transactional
	public SaveStockResponseDto save(@NotNull UUID user_id, @NotNull StockEntity stock) {
		if (user_id == null || stock == null) {
			throw new EntityNotFoundException("Algumas informações não existem. Verifique os dados e tente novamente");
		}
		else if (this.stockRepository.existsByTitleAndAddress(stock.getTitle(), stock.getAddress())) {
			throw new DuplicateRecordException("Já existe um estoque cadastrado com essas informações...");
		}
		Optional<UserEntity> userOptional = this.userRepository.findById(user_id);
		if (userOptional.isEmpty()) {
			throw new EntityNotFoundException("Usuário não encontrado");
		}else {
			UserEntity user = userOptional.get();

			Set<StockEntity> userStocks = user.getStocks();
			Set<UserEntity> stockUsers = stock.getUsers();

			if (userStocks == null) {
				userStocks = new HashSet<StockEntity>();
			}
			if (stockUsers == null) {
				stockUsers = new HashSet<UserEntity>();
			}

			userStocks.add(stock);
			stockUsers.add(user);

			user.setStocks(userStocks);
			stock.setUsers(stockUsers);

			this.stockRepository.save(stock);
			this.userRepository.save(user);

			return this.responseMapper.toDto(stock);
		}

	}

	public SaveStockResponseDto update(UUID id, StockEntity stock) {
		Optional<StockEntity> stockFind = this.stockRepository.findById(id);
		if (stockFind.isEmpty()) {
			throw new EntityNotFoundException("Estoque não encontrado");
		}

		StockEntity stockUpdate = stockFind.get();

		stockUpdate.setId(stockFind.get().getId());
		stockUpdate.setAddress(stock.getAddress());
		stockUpdate.setContact(stock.getContact());
		stockUpdate.setDescription(stock.getDescription());
		stockUpdate.setTitle(stock.getTitle());
		stockUpdate.setTotalCapacity(stock.getTotalCapacity());

		this.stockRepository.save(stockUpdate);
		
		return this.responseMapper.toDto(stockUpdate);
	}

	@Transactional
	public void addUserOnStock(UUID userId, UUID stockId) {
		Optional<UserEntity> userOptional = this.userRepository.findById(userId);
		Optional<StockEntity> stockOptional = this.stockRepository.findById(stockId);

		if (userOptional.isEmpty()) {
			throw new EntityNotFoundException("Usuario nao encontrado");
		}
		UserEntity userEntity = userOptional.get();
		StockEntity stockEntity = stockOptional.get();

		Set<StockEntity> userStocks = userEntity.getStocks();
		Set<UserEntity> stockUsers = stockEntity.getUsers();

		if (userStocks.contains(stockEntity)) {
			throw new DuplicateRecordException("Usuario ja vinculado ao estoque");
		}

		userStocks.add(stockEntity);
		stockUsers.add(userEntity);

		userEntity.setStocks(userStocks);
		stockEntity.setUsers(stockUsers);

		this.userRepository.save(userEntity);
		this.stockRepository.save(stockEntity);

	}
}
