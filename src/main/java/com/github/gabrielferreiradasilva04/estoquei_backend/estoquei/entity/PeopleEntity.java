package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public abstract class PeopleEntity {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private LocalDate registrationDate;
	private LocalDate updateDate;
	private Boolean active;
	
	public PeopleEntity() {
		
	}
	
	
}
