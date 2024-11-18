package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID>{
	
	boolean existsByCodeAndDescriptionAndStock(String code, String description, StockEntity stock);

}
