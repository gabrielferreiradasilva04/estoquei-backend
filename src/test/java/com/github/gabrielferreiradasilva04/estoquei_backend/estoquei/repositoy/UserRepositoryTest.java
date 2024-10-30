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
		List<StockEntity> list = this.repository.findUserStocks(UUID.fromString("5b96b6a2-56fc-4554-b378-d5cf91822072"));
		list.forEach(System.out::println);
	}
	@Test
	public void helloWord() {
		System.out.println("hello word");
	}
}
