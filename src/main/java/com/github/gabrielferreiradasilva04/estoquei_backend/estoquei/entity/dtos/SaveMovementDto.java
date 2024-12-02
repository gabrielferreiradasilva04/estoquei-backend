package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.math.BigDecimal;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.MovementTypeEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductDepositEntity;

public record SaveMovementDto(Double quantity, String observation, BigDecimal total, String invoice, ProductDepositEntity productDeposit, MovementTypeEntity movementType) {

}
