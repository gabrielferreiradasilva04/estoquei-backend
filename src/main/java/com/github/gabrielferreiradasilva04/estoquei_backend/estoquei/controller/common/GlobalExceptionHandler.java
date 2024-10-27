package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.FieldErrorDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/***
	 * Escuta os erros de validação de todos os campos mapeados das entidades na
	 * aplicação, retornando uma mensagem padrão para os erros montada através do 
	 * uso das classes fieldErrorDto e ResponseErrorDto
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getFieldErrors();
		
		List<FieldErrorDto> fieldErrorsDTO = fieldErrors
		.stream()
		.map(fe -> new FieldErrorDto(fe.getField(), fe.getDefaultMessage()))
		.collect(Collectors.toList());
		
		return new ResponseErrorDto(
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de validação", 
				fieldErrorsDTO
				);
	}
}
