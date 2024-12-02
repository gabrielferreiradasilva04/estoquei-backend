package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.MovementTypeEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.MovementTypeRepository;

@Service
public class MovementTypeService {
	
	private final MovementTypeRepository movementTypeRepository;
	
	public MovementTypeService (MovementTypeRepository repository) {
		this.movementTypeRepository = repository;
	}
	
	public List<MovementTypeEntity> findAll(){
		return this.movementTypeRepository.findAll();
	}
}
