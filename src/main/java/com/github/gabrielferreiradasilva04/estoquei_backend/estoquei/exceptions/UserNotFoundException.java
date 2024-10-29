package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String message) {
		super(message);
	}
}
