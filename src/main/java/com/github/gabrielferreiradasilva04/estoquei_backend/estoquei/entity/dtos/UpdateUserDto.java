package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDto(@NotBlank(message="Campo obrigatorio") String firstName, 
		@NotBlank(message="Campo obrigatorio") String lastName, @NotBlank(message="Campo obrigatorio") String phoneNumber,
		@NotBlank(message="Campo obrigatorio") String nickname, @NotNull(message="Campo obrigatorio") Boolean active) {

}
