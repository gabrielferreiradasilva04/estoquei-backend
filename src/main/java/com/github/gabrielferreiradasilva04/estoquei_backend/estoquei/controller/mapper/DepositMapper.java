package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.DepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseDepositDto;

@Mapper(componentModel = "spring")
public interface DepositMapper {
	
	@Mapping(target = "products", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "stock", ignore = true)
	DepositEntity toEntity(ResponseDepositDto dto);
	
	ResponseDepositDto toDto(DepositEntity entity);
}
