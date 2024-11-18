package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ClientEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseClientDto;

@Mapper(componentModel = "spring")
public interface ClientMapper {

	@Mapping(target = "estimates", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "stocks", ignore = true)
	ClientEntity toEntity(ResponseClientDto dto);

	ResponseClientDto toResponseDto(ClientEntity entity);

}
