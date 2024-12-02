package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.BrandEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.CategoryEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.LocationEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UnitMeasureEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SaveProductDto(@NotBlank(message = "Campo obrigatorio") String code, @NotBlank(message = "Campo Obrigatorio") String description, 
		BigDecimal priceCost, BigDecimal salePrice, Boolean active, @NotNull(message = "Selecione um estoque") StockEntity stock, 
		@NotEmpty(message = "Selecione ao menos uma categoria")  List<CategoryEntity> categories, 
		@NotNull(message="Selecione uma localizacao") LocationEntity location,
		@NotNull(message = "Selecione uma unidade de medida") UnitMeasureEntity unitMeasure, @NotNull(message = "Campo obrigatorio") BrandEntity brand) {
}
