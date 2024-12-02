package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.MovementEntity;

public interface MovementRepository extends JpaRepository<MovementEntity, UUID> {

	@Query("""
			FROM MovementEntity m
			JOIN FETCH m.productDeposit pd
			JOIN FETCH pd.deposit
			JOIN FETCH pd.product p
			JOIN FETCH p.unitMeasure u
			JOIN FETCH p.location lo
			JOIN FETCH p.brand b
			JOIN FETCH p.stock s
			WHERE s.id = :stockId
			        """)
	List<MovementEntity> findByProductStock(@Param("stockId") UUID stockId);

}
