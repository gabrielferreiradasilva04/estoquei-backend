package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.util.UUID;

public record ResponseClientDto(UUID id, String firstName, 
		String lastName, String phoneNumber, String email, 
		Boolean active, String observation) {

}
