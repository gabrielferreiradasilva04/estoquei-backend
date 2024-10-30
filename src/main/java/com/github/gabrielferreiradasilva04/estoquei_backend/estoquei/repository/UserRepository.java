package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
	/***
	 * metodo usado pelo spring security para realizar a autenticação do usuário na api
	 * @param email
	 * @return
	 */
	UserDetails findByEmail(String email);
	
	
	@Query("select s from StockEntity s join s.users u where u.id = :userId ")
	List<StockEntity> findUserStocks(@Param("userId") UUID userId);
}