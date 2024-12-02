package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.BrandEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.CategoryEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.LocationEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.SupplierEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UnitMeasureEntity;

public record ResponseProductDto(UUID id,String code,String description, 
		BigDecimal priceCost, BigDecimal salePrice, LocalDate registrationDate, LocalDateTime updateDate, Boolean active,StockEntity stock, LocationEntity location,
		UnitMeasureEntity unitMeasure, Set<SupplierEntity> suppliers, Set<CategoryEntity> categories, BrandEntity brand) {

}
