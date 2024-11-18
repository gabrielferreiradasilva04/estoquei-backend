package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveNewUserDto;

@Mapper(componentModel = "spring") /*trasnforma a interface em um componente spring que pode ser usado em tempo de complicação*/
public interface SaveUserMapper {

	@Mapping(target = "accountNonExpired", ignore = true)
	@Mapping(target = "accountNonLocked", ignore = true)
	@Mapping(target = "credentialsNonExpired", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "stocks", ignore = true)
	UserEntity toEntity(SaveNewUserDto dto);
	
	
	SaveNewUserDto toDto(UserEntity entity);

}
