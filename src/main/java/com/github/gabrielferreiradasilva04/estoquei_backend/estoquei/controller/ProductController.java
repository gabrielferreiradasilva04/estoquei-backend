package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.ProductMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveProductDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.UpdateProductDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.ProductRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService productService;
	private final ProductMapper mapper;
	private final ProductRepository repository;
	
	public ProductController(ProductService service, ProductMapper mapper, ProductRepository repository) {	
		this.productService = service;
		this.mapper = mapper;
		this.repository = repository;
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid SaveProductDto dto){
		try {
			var productEntity = mapper.saveDtoToEntity(dto);
			return ResponseEntity.ok().body(this.productService.save(productEntity));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	@GetMapping
	public ResponseEntity<?> findAllWithMainRelations(){
		try {
			return ResponseEntity.ok().body(this.productService.findAllWithMainRelations());
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@PutMapping("/{productId}")
	public ResponseEntity<?> update(@RequestBody @Valid UpdateProductDto dto, @PathVariable(name = "productId") String idString){
	//	try {
			var productEntity = this.mapper.updateDtoToEntity(dto);
			return ResponseEntity.ok().body(this.productService.update(productEntity));
	//	} catch (Exception e) {
	//		return ResponseEntity.notFound().build();
		//}
			
	}
	@GetMapping("/{id}")
	
	public ResponseEntity<?> findById(@PathVariable String id){
		var uuid = UUID.fromString(id);
		return ResponseEntity.ok().body(this.repository.findByIdWithAllRelations(uuid));
	}
	
	
}
