package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.DepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductEntity;

public record SaveProductDepositDto(UUID id, 
		ProductEntity product, 
		DepositEntity deposit,
		Double quantity,
		Double minimumStock,
		LocalDate registrationDate,
		LocalDateTime updateDate) {
}
