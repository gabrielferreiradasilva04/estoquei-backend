package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.SupplierEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseSupplierDto;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

	
	@Mapping(target = "phoneNumber", ignore = true)
	@Mapping(target = "products", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "stocks", ignore = true)
	SupplierEntity toEntity(ResponseSupplierDto dto);
	
	
	ResponseSupplierDto toResponseDto(SupplierEntity entity);
}
