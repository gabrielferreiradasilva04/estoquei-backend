package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.CategoryEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.CategoryResponseDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveCategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "products", ignore = true)
	@Mapping(target = "stock", ignore = true)

	CategoryEntity saveDtotoEntity(SaveCategoryDto dto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "products", ignore = true)
	@Mapping(target = "stock", ignore = true)
	@Mapping(target = "active", ignore = true)
	CategoryEntity responseDtoToEntity(CategoryResponseDto dto);

	CategoryResponseDto entityToResponseDto(CategoryEntity entity);
	SaveCategoryDto entityToSaveDto(CategoryEntity entity);
	
}
