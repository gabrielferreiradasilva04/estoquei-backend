package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.MovementEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.MovementTypeEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.QuantityMovementException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.MovementRepository;

@Service
public class MovementService {

	private final MovementRepository movementRepository;
	
	public MovementService(MovementRepository movementRepository) {
		this.movementRepository = movementRepository;
	}
	
	public MovementEntity save(MovementEntity m) {
		Double movementQuantity = m.getQuantity();
		Double quantityStock = m.getProductDeposit().getQuantity();
		MovementTypeEntity movementTypeEntity = m.getMovementType();
		if(movementQuantity > quantityStock && movementTypeEntity.getDescription().equals("Saída") ) {
			throw new QuantityMovementException("A quantidade inserida ultrapassa a quantidade disponível em estoque: "+ quantityStock.toString());
		}
		return this.movementRepository.save(m);
	}
	
	public List<MovementEntity> listAll(UUID stockId){
		return this.movementRepository.findByProductStock(stockId);
	}
}
