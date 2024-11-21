package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.util.UUID;

public record UserResponseDto(UUID id, String nickname, String firstName, String lastName, 
		String phoneNumber, String email ) {

}
