package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import java.util.UUID;
import java.util.jar.Attributes.Name;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.CategoryService;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.StockService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private final CategoryService categoryService;
	private final StockService stockService;
	
	public CategoryController(CategoryService catService, StockService stockService) {
		this.categoryService = catService;
		this.stockService = stockService;
	}
	
	@GetMapping("/{stockId}")
	public ResponseEntity<?> listAll(@PathVariable String stockId){
		try {
			var uuid = UUID.fromString(stockId);
			StockEntity stock = this.stockService.findByid(uuid);
			return ResponseEntity.ok().body(this.categoryService.listAll(stock));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
