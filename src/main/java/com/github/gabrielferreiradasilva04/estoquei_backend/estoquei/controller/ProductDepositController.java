package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.ProductDepositMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductDepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveProductDepositDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.ProductDepositService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productDeposits")
public class ProductDepositController {

	private final ProductDepositService productDepositService;
	private final ProductDepositMapper productDepositMapper;
	
	public ProductDepositController(ProductDepositService productDepositService, ProductDepositMapper productDepositMapper) {
		this.productDepositService = productDepositService;
		this.productDepositMapper = productDepositMapper;
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid SaveProductDepositDto dto){
			ProductDepositEntity entity = this.productDepositMapper.toEntity(dto);
			return ResponseEntity.ok().body(this.productDepositService.save(entity));
	}
	
	
	@GetMapping("/{stockId}")
	public ResponseEntity<?> findAll(@PathVariable String stockId){
		try {
			var uuid = UUID.fromString(stockId);
			return ResponseEntity.ok().body(this.productDepositService.findAll(uuid));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
