package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ResponseErrorDto(int status, String message, List<FieldErrorDto> errors) {
	
	public static ResponseErrorDto defaultResponse(String message) {
		return new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(), message, List.of());
	}
	public static ResponseErrorDto conflict(String message) {
		return new ResponseErrorDto(HttpStatus.CONFLICT.value(), message, List.of());
	}

}
