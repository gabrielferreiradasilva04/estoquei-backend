package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.DepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductDepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductEntity;

public interface ProductDepositRepository extends JpaRepository<ProductDepositEntity, UUID> {
	boolean existsByProductAndDeposit(ProductEntity product, DepositEntity deposit);
	
	@Query("SELECT pd FROM ProductDepositEntity pd " +
		       "JOIN FETCH pd.product p " +
		       "JOIN FETCH p.unitMeasure u " +
		       "JOIN FETCH p.location l " +
		       "JOIN FETCH p.brand b "+
		       "JOIN FETCH p.stock s " +
		       "JOIN FETCH pd.deposit dp "+
		       "WHERE s.id = :stockId")
		List<ProductDepositEntity> findProductDepositsByStockId(@Param("stockId") UUID stockId);

}
