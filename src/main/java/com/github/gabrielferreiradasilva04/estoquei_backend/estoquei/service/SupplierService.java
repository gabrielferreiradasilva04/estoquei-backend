package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.SupplierMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.SupplierEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseSupplierDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.SupplierRepository;

@Service
public class SupplierService {

	private final SupplierRepository supplierRepository;
	private final SupplierMapper supplierMapper;
	
	public SupplierService(SupplierRepository repository, SupplierMapper mapper) {
		this.supplierRepository = repository;
		this.supplierMapper = mapper;
	}
	
	public List<ResponseSupplierDto> listAll(){
		List<SupplierEntity> listEntity = this.supplierRepository.findAll();
		List<ResponseSupplierDto> listDto = listEntity.stream()
				.map(entity -> this.supplierMapper.toResponseDto(entity))
				.collect(Collectors.toList());
		return listDto;
	}
}
