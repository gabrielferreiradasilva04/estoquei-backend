package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.StockRepository;

@RestController
@RequestMapping("/stocks")
public class StockController {

	private StockRepository stockRepository;
	
	public StockController(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
	
	@GetMapping
	public ResponseEntity<Object> findAllStocks(){
		List<StockEntity> list = this.stockRepository.findAll();
		return ResponseEntity.ok().body(list);
		
	}
}
