package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.BrandEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, UUID>{
	/***
	 * encontrar as marcas por estoque cadastrado
	 * @param stock
	 * @return
	 */
	List<BrandEntity> findByStock(StockEntity stock);

}
