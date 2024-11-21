package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID>{
		
	boolean existsByCodeAndDescriptionAndStock(String code, String description, StockEntity stock);
	
	@Query("""
			select p from ProductEntity p
			join fetch p.unitMeasure u
			join fetch p.location l
			join fetch p.stock
			""")
	List<ProductEntity> findAllWithMainRelations();
	
	@Query("""
	        select p from ProductEntity p
	        join fetch p.unitMeasure u
	        join fetch p.location l
	        join fetch p.stock s
	        join fetch p.suppliers sup
	        join fetch p.categories c
	        join fetch p.deposits d
	        where p.id = :id
	        """)
	Optional<ProductEntity> findByIdWithAllRelations(@Param("id") UUID id);

}
