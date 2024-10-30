package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;

public interface StockRepository extends JpaRepository<StockEntity, UUID> {
	
	@Query("Select u from UserEntity u join u.stocks s where s.id = :stockId")
	Set<UserEntity> findStockUsers(@Param("stockId") UUID stockId);
	
}
