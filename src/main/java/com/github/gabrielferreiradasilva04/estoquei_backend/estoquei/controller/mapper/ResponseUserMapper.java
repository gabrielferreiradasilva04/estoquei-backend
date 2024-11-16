package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.UserResponseDto;

@Mapper(componentModel = "spring")
public interface ResponseUserMapper {
	
	UserResponseDto toDto (UserEntity entity);
}
