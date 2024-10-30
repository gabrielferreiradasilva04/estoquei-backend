package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import jakarta.validation.constraints.NotBlank;

public record SaveStockDto(
		@NotBlank(message="Campo obrigatorio") String title,
		@NotBlank(message="Campo obrigatorio") String description,
		@NotBlank(message="Campo obrigatorio") String address, 
		@NotBlank(message="Campo obrigatorio") String contact, 
		Double totalCapacity) {

}
