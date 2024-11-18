package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	private final ClientService service;
	
	public ClientController(ClientService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		try {
			return ResponseEntity.ok().body(this.service.findAll());
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
