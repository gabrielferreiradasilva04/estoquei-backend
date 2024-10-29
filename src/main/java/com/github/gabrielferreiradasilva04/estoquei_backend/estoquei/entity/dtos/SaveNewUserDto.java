package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.config.UserRole;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserEntityDto(@NotBlank(message="Campo obrigatorio") String firstName, 
		@NotBlank(message="Campo obrigatorio") String lastName, @NotBlank(message="Campo obrigatorio") String phoneNumber,
		@NotBlank(message="Campo obrigatorio") String email, @NotBlank(message="Campo obrigatorio") String password,
		@NotBlank(message="Campo obrigatorio") String nickname, @NotNull(message="Campo obrigatorio") Boolean active,
		@NotNull (message= "Campo Obrigatorio") UserRole userType) {
	
	public UserEntity toUserEntity() {
		UserEntity userEntity = new UserEntity();
		userEntity.setActive(this.active);
		userEntity.setNickname(this.nickname);
		userEntity.setFirstName(this.firstName);
		userEntity.setLastName(this.lastName);
		userEntity.setEmail(this.email);
		userEntity.setPassword(this.password);
		userEntity.setPhoneNumber(this.phoneNumber);
		userEntity.setUserType(this.userType);
		
		
		return userEntity;
	}
		
}
