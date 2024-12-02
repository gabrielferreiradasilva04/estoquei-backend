package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.MovementTypeService;

@RestController
@RequestMapping("/movementTypes")
public class MovementTypeController {

	private final MovementTypeService service;
	
	public MovementTypeController (MovementTypeService service) {
		this.service = service;
	}
	
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok().body(this.service.findAll());
	}
}
