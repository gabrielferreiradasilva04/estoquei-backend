package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repositoy;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.StockRepository;

@SpringBootTest
public class StockRepositoryTest {

	
	@Autowired
	private StockRepository repository;
	@Test
	public void findStockUsers() {
		Set<UserEntity> list = this.repository.findStockUsers(UUID.fromString("fa848225-c7f1-477c-8994-aef551ea54d6"));
		System.out.println(list);
	}
}
