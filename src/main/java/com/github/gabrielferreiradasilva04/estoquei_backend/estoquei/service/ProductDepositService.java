package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductDepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.DuplicateRecordException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.ProductDepositRepository;

@Service
public class ProductDepositService {
	private final ProductDepositRepository productDepositRepository;
	
	public ProductDepositService(ProductDepositRepository productDepositRepository) {
		this.productDepositRepository = productDepositRepository;
	}
	
	public ProductDepositEntity save(ProductDepositEntity pde) {
		if(this.productDepositRepository.existsByProductAndDeposit(pde.getProduct(), pde.getDeposit())) {
			throw new DuplicateRecordException("Esse produto já está vinculado a esse deposito");
		}
		return this.productDepositRepository.save(pde);
	}
	
	public List<ProductDepositEntity> findAll(UUID stockId) {
		return this.productDepositRepository.findProductDepositsByStockId(stockId);
	}
}
