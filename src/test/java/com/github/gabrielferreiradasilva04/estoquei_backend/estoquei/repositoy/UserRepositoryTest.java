package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repositoy;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private  UserRepository repository;
	
	
	@Test
	public void findUserStocks(){
		List<StockEntity> list = this.repository.findUserStocks(UUID.fromString("cc7233de-be31-42f0-9a2d-e812ca0d2776"));
		System.out.println(list);
	}
	@Test
	public void helloWord() {
		System.out.println("hello word");
	}
}
