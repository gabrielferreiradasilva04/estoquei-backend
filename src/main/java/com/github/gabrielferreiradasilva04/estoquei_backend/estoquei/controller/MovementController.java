package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.MovementMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.MovementEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveMovementDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.MovementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movements")
public class MovementController {

	private final MovementService service;
	private final MovementMapper movementMapper;
	
	public MovementController(MovementService movementService, MovementMapper movementMapper) {
		this.service = movementService;
		this.movementMapper = movementMapper;
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid SaveMovementDto dto){
		try {
			MovementEntity entity = this.movementMapper.toentity(dto);
			return ResponseEntity.ok().body(this.service.save(entity));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{stockId}")
	public ResponseEntity<?> findAll(@PathVariable String stockId){
		try {
			var uuid = UUID.fromString(stockId);
			return ResponseEntity.ok().body(this.service.listAll(uuid));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
