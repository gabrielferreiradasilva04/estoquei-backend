package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UnitMeasureEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseUnitMeasureDto;

@Mapper(componentModel = "spring")
public interface UnitMeasureMapper {

	@Mapping(target = "products", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	UnitMeasureEntity toEntity(ResponseUnitMeasureDto dto);
	
	ResponseUnitMeasureDto toDto(UnitMeasureEntity entity);
}
