package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.CategoryMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.CategoryEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.CategoryResponseDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.CategoryRepository;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	
	public CategoryService( CategoryRepository catRepository, CategoryMapper mapper) {
		this.categoryRepository = catRepository;
		this.categoryMapper = mapper;
	}
	
	public List<CategoryResponseDto> listAll(){
		List<CategoryEntity> list = this.categoryRepository.findAll();
		List<CategoryResponseDto> listDto = list.stream()
				.map(entity -> this.categoryMapper.entityToResponseDto(entity))
				.collect(Collectors.toList());
		
		return listDto;
	}

}
