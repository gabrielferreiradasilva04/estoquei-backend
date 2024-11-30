package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.BrandEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseBrandDto;

@Mapper(componentModel = "spring")
public interface BrandMapper {

	@Mapping(target = "stock", ignore = true)
	@Mapping(target = "productEntities", ignore = true)
	BrandEntity dtoToEntity(ResponseBrandDto dto);
	ResponseBrandDto entityToDto(BrandEntity entity);
}
