package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.DepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;

public interface DepositRepository extends JpaRepository<DepositEntity, UUID>{
	List<DepositEntity> findByStock(StockEntity stock);
}
