package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEntityTests {

	@Test
	void createEntity() {
		UserEntity user = new UserEntity();
		user.setFirstName("Gabriel");
		user.setLastName("Silva");
		user.setNickname("gabriel_silva");
		user.setActive(Boolean.TRUE);
		user.setEmail("admin@admin");
		user.setPassword("123");
		user.setPhoneNumber("41 996241805");
		user.setRegistrationDate(LocalDate.now());
		user.setUpdateDate(LocalDate.now());
		
		System.out.println("ATRIBUTOS DO USUÁRIO : "+ user);
	}
}
