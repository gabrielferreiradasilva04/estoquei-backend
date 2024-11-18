package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.LocationEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseLocationDto;

@Mapper(componentModel = "spring")
public interface LocationMapper {

	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "stock", ignore = true)
	@Mapping(target = "products", ignore = true)
	LocationEntity toEntity(ResponseLocationDto dto);
	
	ResponseLocationDto toDto(LocationEntity entity);
}
