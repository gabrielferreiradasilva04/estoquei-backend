package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveStockDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveStockResponseDto;

@Mapper(componentModel = "spring")

public interface SaveStockResponseMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "users", ignore = true)
	StockEntity toEntity(SaveStockDto dto);
	
	SaveStockResponseDto toDto (StockEntity entity);
}
