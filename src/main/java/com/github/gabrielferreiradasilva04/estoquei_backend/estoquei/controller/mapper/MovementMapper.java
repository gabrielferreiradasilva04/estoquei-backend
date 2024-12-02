package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.MovementEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveMovementDto;

@Mapper(componentModel = "spring")
public interface MovementMapper {
	
	@Mapping(target = "date", ignore =  true)
	@Mapping(target = "id", ignore = true)
	MovementEntity toentity(SaveMovementDto dto); 
	
	SaveMovementDto todto(MovementEntity entity);
}
