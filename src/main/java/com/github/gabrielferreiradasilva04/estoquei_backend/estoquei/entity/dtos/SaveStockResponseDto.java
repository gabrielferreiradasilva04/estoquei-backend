package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.util.UUID;

public record SaveStockResponseDto(UUID id, String title, String description, String address, String contact,
		Double totalCapacity) {

}
