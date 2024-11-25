package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.CategoryEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
	
	/***
	 * encontra todas as categorias relacionadas com aquele estoque pesquisado
	 * @param stock
	 * @return
	 */
	List<CategoryEntity> findByStock(StockEntity stock);
	
}
