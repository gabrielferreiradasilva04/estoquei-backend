package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private final CategoryService categoryService;
	
	public CategoryController(CategoryService catService) {
		this.categoryService = catService;
	}
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		try {
			return ResponseEntity.ok().body(this.categoryService.listAll());
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
