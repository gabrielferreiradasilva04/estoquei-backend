package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.UpdateUserDto;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper {

	@Mapping(target = "accountNonExpired", ignore = true)
	@Mapping(target = "accountNonLocked", ignore = true)
	@Mapping(target = "credentialsNonExpired", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "userType", ignore = true)
	@Mapping(target = "email", ignore = true)
	UserEntity toEntity(UpdateUserDto dto);
	
	
	UpdateUserDto toDto(UserEntity entity);
	
}
