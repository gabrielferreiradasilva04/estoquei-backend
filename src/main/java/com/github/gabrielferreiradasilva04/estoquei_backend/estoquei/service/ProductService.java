package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.ProductMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.CategoryEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.DepositEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.LocationEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.SupplierEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UnitMeasureEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseProductDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.DuplicateRecordException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.EntityNotFoundException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.CategoryRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.DepositRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.LocationRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.ProductRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.StockRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.SupplierRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UnitMeasureRepository;

@Service
public class ProductService {

	private final ProductRepository repository;
	private final SupplierRepository supRepository;
	private final CategoryRepository catRepository;
	private final DepositRepository depRepository;
	private final UnitMeasureRepository unitMeasureRepository;
	private final StockRepository stockRepository;
	private final LocationRepository locationRepository;
	private final ProductMapper mapper;
	
	
	public ProductService(ProductRepository productRepository, SupplierRepository supRepository, CategoryRepository catRepository, DepositRepository depositRepository,
			UnitMeasureRepository unitMeasureRepository, StockRepository stockRepository, LocationRepository locationRepository, ProductMapper mapper) {
		this.repository = productRepository;
		this.supRepository = supRepository;
		this.catRepository = catRepository;
		this.depRepository = depositRepository;
		this.unitMeasureRepository = unitMeasureRepository;
		this.stockRepository = stockRepository;
		this.locationRepository = locationRepository;
		this.mapper = mapper;
	}
	
	@Transactional
	public ProductEntity save(ProductEntity p) {
		if(this.repository.existsByCodeAndDescriptionAndStock(p.getCode(), p.getDescription(), p.getStock())) {
			throw new DuplicateRecordException("Esse produto já existe nesse estoque");
		}
		//verificar fornecedores
		List<UUID> supplierIds = p.getSuppliers().stream().map(entity -> entity.getId()).collect(Collectors.toList());
		List<SupplierEntity> suppliers = supRepository.findAllById(supplierIds);
		if (suppliers.size() != supplierIds.size()) {
		    throw new EntityNotFoundException("Um ou mais fornecedores não foram encontrados");
		}
		
		//validar categorias
		Set<UUID> categoryIds = p.getCategories()
				.stream()
				.map(e -> e.getId())
				.collect(Collectors.toSet());
		List<CategoryEntity> categories = catRepository.findAllById(categoryIds);
		if(categories.size() != categoryIds.size()) {
		    throw new EntityNotFoundException("Uma ou mais categorias não foram encontradas");
		}
		//validar depositos
		List<UUID> despositsIds = p.getDeposits()
				.stream()
				.map(e -> e.getId())
				.collect(Collectors.toList());
		List<DepositEntity> deposits = depRepository.findAllById(despositsIds);
		if(deposits.size() != despositsIds.size()) {
		    throw new EntityNotFoundException("Um ou mais depositos não foram encontrados");
		}
		//validar a unidade demedida
		var unitMeasureOptional = this.unitMeasureRepository.findById(p.getUnitMeasure().getId());
		if(unitMeasureOptional.isEmpty()) {
			throw new EntityNotFoundException("Unidade de medida não encontrada");
		}
		
		//validar estoque
		var stockOptional = stockRepository.findById(p.getStock().getId());
		if(stockOptional.isEmpty()) {
			throw new EntityNotFoundException("Estoque não encontrado");
		}
		//validar localizacao
		var locationOptional = this.locationRepository.findById(p.getLocation().getId());
		if(locationOptional.isEmpty()) {
			throw new EntityNotFoundException("Localização não encontrada");
		}
		
		// Normatizar dados
		String code = p.getCode().toLowerCase().trim();
		String description = p.getDescription().toLowerCase().trim();
		
		StockEntity stock = stockOptional.get();
		UnitMeasureEntity unitMeasure = unitMeasureOptional.get();
		LocationEntity location = locationOptional.get();
		
		//persistencia do relacionamento JPA
		suppliers.forEach(supplier -> {
			if(!supplier.getProducts().contains(p)) {
				supplier.getProducts().add(p);
			}
		});
	    categories.forEach(category -> {
			if(!category.getProducts().contains(p)) {
				category.getProducts().add(p);
			}
		});
	    deposits.forEach(deposit -> {
			if(!deposit.getProducts().contains(p)) {
				deposit.getProducts().add(p);
			}
		});
		
	    //setar novamente os dados normalizados
		p.setStock(stock);
		p.setUnitMeasure(unitMeasure);
		p.setLocation(location);
		p.setCode(code);
		p.setDescription(description);
		p.setSuppliers(suppliers.stream().collect(Collectors.toSet()));
		p.setCategories(categories.stream().collect(Collectors.toSet()));
		p.setDeposits(deposits.stream().collect(Collectors.toSet()));
		
		//salvar
		return this.repository.save(p);
	
	}
	
	@Transactional
	public List<ResponseProductDto> findAllWithMainRelations(){
		List<ProductEntity> entityList =  this.repository.findAllWithMainRelations();
		List<ResponseProductDto> dtos = entityList.stream()
				.map(p -> this.mapper.entityToResponseDto(p))
				.collect(Collectors.toList());
		
		return dtos;
	}
	
	@Transactional
	public ResponseProductDto update(ProductEntity p) {
	    if (p.getId() == null) {
	        throw new EntityNotFoundException("Produto não encontrado");
	    }
	    var pOptional = this.repository.findByIdWithAllRelations(p.getId());
	    if (pOptional.isEmpty()) {
	        throw new EntityNotFoundException("Produto não encontrado");
	    }
	    ProductEntity pUpdate = pOptional.get();

	    // Atualizar os campos básicos
	    pUpdate.setCode(p.getCode().trim().toLowerCase());
	    pUpdate.setDescription(p.getDescription().trim().toLowerCase());
	    pUpdate.setActive(p.getActive());
	    pUpdate.setMinimumStock(p.getMinimumStock());
	    pUpdate.setPriceCost(p.getPriceCost());
	    pUpdate.setQuantityStock(p.getQuantityStock());
	    pUpdate.setSalePrice(p.getSalePrice());

	    // Atualizar o Location e UnitMeasure
	    if (p.getLocation() != null) {
	        pUpdate.setLocation(p.getLocation());
	    }

	    if (p.getUnitMeasure() != null) {
	        pUpdate.setUnitMeasure(p.getUnitMeasure());
	    }

	    // Atualizar categorias, fornecedores e depósitos
	    if (!p.getCategories().isEmpty()) {
	        pUpdate.getCategories().clear();
	        pUpdate.setCategories(p.getCategories());
	    }

	    if (!p.getSuppliers().isEmpty()) {
	        pUpdate.getSuppliers().clear();
	        pUpdate.setSuppliers(p.getSuppliers());
	    }

	    if (!p.getDeposits().isEmpty()) {
	        pUpdate.getDeposits().clear();
	        pUpdate.setDeposits(p.getDeposits());
	    }

	    // Atualizar os relacionamentos nas entidades associadas
	    Set<CategoryEntity> newCategories = pUpdate.getCategories();
	    Set<SupplierEntity> newSuppliers = pUpdate.getSuppliers();
	    Set<DepositEntity> newDeposits = pUpdate.getDeposits();

	    for (CategoryEntity c : newCategories) {
	        if (!c.getProducts().contains(pUpdate)) {
	            c.getProducts().add(pUpdate);
	        }
	    }
	    for (DepositEntity d : newDeposits) {
	        if (!d.getProducts().contains(pUpdate)) {
	            d.getProducts().add(pUpdate);
	        }
	    }
	    for (SupplierEntity s : newSuppliers) {
	        if (!s.getProducts().contains(pUpdate)) {
	            s.getProducts().add(pUpdate);
	        }
	    }

	    // Persistir as atualizações
	    this.repository.save(pUpdate);

	    return this.mapper.entityToResponseDto(pUpdate);
	}

	
	
}
