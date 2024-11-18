package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.util.UUID;

public record ResponseSupplierDto(UUID id, String name, String document,
		String address, String email, String siteUrl, String observation, 
		Boolean active, String phoneNumber) {

}
