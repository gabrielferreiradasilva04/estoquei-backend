package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.math.BigDecimal;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.LocationEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UnitMeasureEntity;

public record ResponseProductDto(String code,String description, 
		BigDecimal priceCost, BigDecimal salePrice, 
		Double minimumStock, Boolean active,StockEntity stock, LocationEntity location,
		UnitMeasureEntity unitMeasure) {

}
