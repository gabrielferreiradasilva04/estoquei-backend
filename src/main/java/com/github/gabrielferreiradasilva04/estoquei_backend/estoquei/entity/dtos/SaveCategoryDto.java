package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveCategoryDto(@NotBlank(message = "Campo obrigatório") String description, @NotNull Boolean active) {

}
