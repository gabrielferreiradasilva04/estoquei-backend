package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.BrandMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.BrandEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseBrandDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.BrandRepository;

@Service
public class BrandService {

	private final BrandRepository repository;
	private final BrandMapper mapper;
	
	public BrandService (BrandRepository repository, BrandMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public List<ResponseBrandDto> findAll(StockEntity stock){
		List<BrandEntity> entities = this.repository.findByStock(stock);
		List<ResponseBrandDto> dtos = entities.stream().map(e -> this.mapper.entityToDto(e)).collect(Collectors.toList());
		return dtos;
	}
}
