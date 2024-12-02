package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductDepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveProductDepositDto;

@Mapper(componentModel = "spring")
public interface ProductDepositMapper {

	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "movements", ignore = true)
	ProductDepositEntity toEntity(SaveProductDepositDto dto);  
	
	SaveProductDepositDto toDto(ProductDepositEntity entity);
}
