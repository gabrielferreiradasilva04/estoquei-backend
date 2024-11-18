package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.UnitMeasureMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UnitMeasureEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseUnitMeasureDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UnitMeasureRepository;

@Service
public class UnitMeasureService {

	private final UnitMeasureRepository repository;
	private final UnitMeasureMapper mapper;
	
	public UnitMeasureService(UnitMeasureRepository unitMeasureRepository, UnitMeasureMapper mapper) {
		this.repository = unitMeasureRepository;
		this.mapper = mapper;
	}
	
	
	public List<ResponseUnitMeasureDto> findAll(){
		List<UnitMeasureEntity> entities = this.repository.findAll();
		List<ResponseUnitMeasureDto> dtos = entities
				.stream()
				.map(entity -> mapper.toDto(entity))
				.collect(Collectors.toList());
		return dtos;
	}
}
