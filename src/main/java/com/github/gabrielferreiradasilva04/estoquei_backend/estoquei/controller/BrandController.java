package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.BrandService;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.StockService;

@RestController
@RequestMapping("/brands")
public class BrandController {
	private final BrandService service;
	private final StockService stockService;
	public BrandController (BrandService service, StockService stockService) {
		this.service = service;
		this.stockService = stockService;
	}
	
	@GetMapping("/{stockId}")
	public ResponseEntity<?> listAll(@PathVariable String stockId){
		try {
			var stockUUID = UUID.fromString(stockId);
			StockEntity stock = this.stockService.findByid(stockUUID);
			return ResponseEntity.ok().body(this.service.findAll(stock));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
