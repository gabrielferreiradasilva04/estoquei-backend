package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

	boolean existsByCodeAndDescriptionAndStock(String code, String description, StockEntity stock);

	@Query("""
			select distinct p from ProductEntity p
			left join fetch p.unitMeasure u
			left join fetch p.location l
			left join fetch p.stock s
			left join fetch p.suppliers r
			left join fetch p.categories c
			left join fetch p.brand b
			left join fetch p.productDeposits pde
			""")
	List<ProductEntity> findAllWithMainRelations();

	@Query("""
			select distinct p from ProductEntity p
			left join fetch p.unitMeasure u
			left join fetch p.location l
			left join fetch p.stock s
			left join fetch p.suppliers r
			left join fetch p.categories c
			left join fetch p.brand b
			left join fetch p.productDeposits pde
			where p.id = :id
			     """)
	Optional<ProductEntity> findByIdWithAllRelations(@Param("id") UUID id);

}
