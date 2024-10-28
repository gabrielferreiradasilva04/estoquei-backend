package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDto(
		@NotBlank(message = "Campo obrigatorio") String email
		, @NotBlank(message = "Campo obrigatorio") String password
		) {}
