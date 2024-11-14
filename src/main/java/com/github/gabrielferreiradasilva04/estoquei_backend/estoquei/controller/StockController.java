package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.SaveStockMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveStockDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stocks")
public class StockController {

	private final StockService stockService;
	private final SaveStockMapper saveStockMapper;

	public StockController(StockService stockService, SaveStockMapper saveStockMapper) {
		this.stockService = stockService;
		this.saveStockMapper = saveStockMapper;
	}

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid SaveStockDto dto) {
		try {
			UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			StockEntity stock = this.saveStockMapper.toEntity(dto);
			return ResponseEntity.ok().body(this.stockService.save(user.getId(), stock));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid SaveStockDto dto) {
		try {
			UUID sotckId = UUID.fromString(id);
			StockEntity stock = this.saveStockMapper.toEntity(dto);
			return ResponseEntity.ok().body(this.stockService.update(sotckId, stock));

		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{stockId}/{userId}")
	public ResponseEntity<Object> addUserOnStock(@PathVariable String userId, 
			@PathVariable String stockId){
		try {
			UUID userUUID = UUID.fromString(userId);
			UUID stockUUI = UUID.fromString(stockId);
			
			this.stockService.addUserOnStock(userUUID, stockUUI);
			return ResponseEntity.ok().build();
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
