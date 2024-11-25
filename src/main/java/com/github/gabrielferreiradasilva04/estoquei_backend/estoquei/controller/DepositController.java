package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.DepositService;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.StockService;

@RestController
@RequestMapping("deposits")
public class DepositController {
	
	private final DepositService service;
	private final StockService stockService;
	
	public DepositController(DepositService service, StockService stockService) {
		this.service = service;
		this.stockService = stockService;
	}
	
	@GetMapping("/{stockId}")
	public ResponseEntity<?> findAll(@PathVariable String stockId){
		try {
			var uuid = UUID.fromString(stockId);
			StockEntity stockEntity = this.stockService.findByid(uuid);
			return ResponseEntity.ok().body(this.service.findAll(stockEntity));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
