package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.DepositMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.DepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseDepositDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.DepositRepository;

@Service
public class DepositService {

	private final DepositRepository depositRepository;
	private final DepositMapper mapper;
	
	public DepositService(DepositRepository depositRepository, DepositMapper mapper) {
		this.depositRepository = depositRepository;
		this.mapper = mapper;
	}
	
	public List<ResponseDepositDto> findAll(StockEntity stock){
		List<DepositEntity> listEntity = this.depositRepository.findByStock(stock);
		List<ResponseDepositDto> listDto = listEntity.stream()
				.map(entity -> this.mapper.toDto(entity))
				.collect(Collectors.toList());
		
		return listDto;
	}
}
