package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
	
	private final SupplierService service;
	
	public SupplierController(SupplierService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		try {
			return ResponseEntity.ok().body(this.service.listAll());
		} catch (Exception e) {
			System.out.println(e.getCause());
			return ResponseEntity.notFound().build();
		}
	}
}
