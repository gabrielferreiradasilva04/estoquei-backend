package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.ProductMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.BrandEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.CategoryEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.LocationEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.StockEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.SupplierEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UnitMeasureEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseProductDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.DuplicateRecordException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.exceptions.EntityNotFoundException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.BrandRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.CategoryRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.LocationRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.ProductRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.StockRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.SupplierRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UnitMeasureRepository;

@Service
public class ProductService {

	private final ProductRepository repository;
	private final CategoryRepository catRepository;
	private final UnitMeasureRepository unitMeasureRepository;
	private final StockRepository stockRepository;
	private final LocationRepository locationRepository;
	private final ProductMapper mapper;
	private final BrandRepository brandRepository;
	private final SupplierRepository supplierRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository catRepository,
			UnitMeasureRepository unitMeasureRepository, StockRepository stockRepository,
			LocationRepository locationRepository, ProductMapper mapper, BrandRepository brandRepository,
			SupplierRepository supplierRepository) {
		this.repository = productRepository;
		this.catRepository = catRepository;
		this.unitMeasureRepository = unitMeasureRepository;
		this.stockRepository = stockRepository;
		this.locationRepository = locationRepository;
		this.mapper = mapper;
		this.brandRepository = brandRepository;
		this.supplierRepository = supplierRepository;
	}

	@Transactional
	public ProductEntity save(ProductEntity p) {
		if (this.repository.existsByCodeAndDescriptionAndStock(p.getCode(), p.getDescription(), p.getStock())) {
			throw new DuplicateRecordException("Esse produto já existe nesse estoque");
		}
		// validar categorias do produto
		Set<UUID> categoryIds = p.getCategories().stream().map(e -> e.getId()).collect(Collectors.toSet());
		List<CategoryEntity> categories = catRepository.findAllById(categoryIds);
		if (categories.size() != categoryIds.size()) {
			throw new EntityNotFoundException("Uma ou mais categorias não foram encontradas");
		}
		// validar localização do produto
		var locationOptional = this.locationRepository.findById(p.getLocation().getId());
		if (locationOptional.isEmpty()) {
			throw new EntityNotFoundException("Localização não encontrada");
		}
		// validar a unidade de medida
		var unitMeasureOptional = this.unitMeasureRepository.findById(p.getUnitMeasure().getId());
		if (unitMeasureOptional.isEmpty()) {
			throw new EntityNotFoundException("Unidade de medida não encontrada");
		}
		// validar estoque
		var stockOptional = stockRepository.findById(p.getStock().getId());
		if (stockOptional.isEmpty()) {
			throw new EntityNotFoundException("Estoque não encontrado");
		}
		// validar brands
		var brandOptional = this.brandRepository.findById(p.getBrand().getId());
		if (brandOptional.isEmpty()) {
			throw new EntityNotFoundException("Marca não encontrada");
		}
		// persistir no banco de dados o relacionamento bidirecional
		categories.forEach(category -> {
			if (!category.getProducts().contains(p)) {
				category.getProducts().add(p);
			}
		});

		// Normatizar dados
		String code = p.getCode().toLowerCase().trim();
		String description = p.getDescription().toLowerCase().trim();

		StockEntity stock = stockOptional.get();
		UnitMeasureEntity unitMeasure = unitMeasureOptional.get();
		LocationEntity location = locationOptional.get();
		BrandEntity brand = brandOptional.get();

		p.setStock(stock);
		p.setBrand(brand);
		p.setUnitMeasure(unitMeasure);
		p.setLocation(location);
		p.setCode(code);
		p.setDescription(description);
		p.setCategories(categories.stream().collect(Collectors.toSet()));
		return this.repository.save(p);
	}

	@Transactional
	public List<ResponseProductDto> findAllWithMainRelations() {
		List<ProductEntity> entityList = this.repository.findAllWithMainRelations();
		List<ResponseProductDto> dtos = entityList.stream().map(p -> this.mapper.entityToResponseDto(p))
				.collect(Collectors.toList());

		return dtos;
	}

	/***
	 * metodo que realiza o update do produto mas não atualiza os estoques dele
	 * 
	 * @param p
	 * @return
	 */
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

		updateDefaultFields(p, pUpdate);

		if (p.getLocation() != null) {
			pUpdate.setLocation(p.getLocation());
		}

		if (p.getUnitMeasure() != null) {
			pUpdate.setUnitMeasure(p.getUnitMeasure());
		}

		if (!p.getCategories().isEmpty()) {
			updateCategories(p, pUpdate);
		}

		if (!p.getSuppliers().isEmpty()) {
			pUpdate = addSuppliers(p, pUpdate);
		} else {
			removeSuppliers(p, pUpdate);
		}

		if (p.getBrand() != null) {
			pUpdate.setBrand(p.getBrand());
		}

		this.repository.save(pUpdate);

		return this.mapper.entityToResponseDto(pUpdate);
	}

	private ProductEntity updateDefaultFields(ProductEntity p, ProductEntity pUpdate) {
		pUpdate.setCode(p.getCode().trim().toLowerCase());
		pUpdate.setDescription(p.getDescription().trim().toLowerCase());
		pUpdate.setActive(p.getActive());
		pUpdate.setMinimumStock(p.getMinimumStock());
		pUpdate.setPriceCost(p.getPriceCost());
		pUpdate.setQuantityStock(p.getQuantityStock());
		pUpdate.setSalePrice(p.getSalePrice());
		
		return pUpdate;
	}

	@Transactional
	private ProductEntity removeSuppliers(ProductEntity p, ProductEntity pUpdate) {
	    // Recuperar os fornecedores atualizados do produto
	    Set<SupplierEntity> updatedSuppliers = p.getSuppliers().stream()
	            .map(sup -> supplierRepository.findById(sup.getId()).orElseThrow(
	                    () -> new EntityNotFoundException("Fornecedor não encontrado: " + sup.getName())))
	            .collect(Collectors.toSet());

	    // Fornecedores associados antes da atualização
	    Set<SupplierEntity> oldSuppliers = new HashSet<>(pUpdate.getSuppliers());

	    if (!updatedSuppliers.isEmpty()) {
	        // Remover relação com fornecedores que não estão mais associados
	        oldSuppliers.stream()
	                .filter(oldSupplier -> !updatedSuppliers.contains(oldSupplier))
	                .forEach(oldSupplier -> oldSupplier.getProducts().remove(pUpdate));

	        // Atualizar fornecedores no produto
	        pUpdate.getSuppliers().clear();
	        pUpdate.getSuppliers().addAll(updatedSuppliers);

	        // Garantir que novos fornecedores tenham o produto associado
	        updatedSuppliers.forEach(supplier -> {
	            if (!supplier.getProducts().contains(pUpdate)) {
	                supplier.getProducts().add(pUpdate);
	            }
	        });
	    } else {
	        // Caso nenhum fornecedor seja passado, remover todos os fornecedores
	        oldSuppliers.forEach(oldSupplier -> oldSupplier.getProducts().remove(pUpdate));
	        pUpdate.getSuppliers().clear();
	    }

	    return pUpdate;
	}


	private ProductEntity addSuppliers(ProductEntity p, ProductEntity pUpdate) {
		Set<SupplierEntity> newSuppliers = p.getSuppliers().stream()
				.map(sup -> supplierRepository.findById(sup.getId()).orElseThrow(
						() -> new EntityNotFoundException("Fornecedor não encontrado: " + sup.getName())))
				.collect(Collectors.toSet());

		// Atualizar fornecedores no produto
		pUpdate.getSuppliers().clear();
		pUpdate.getSuppliers().addAll(newSuppliers);

		// Atualizar relacionamento bidirecional
		newSuppliers.forEach(supplier -> {
			if (!supplier.getProducts().contains(pUpdate)) {
				supplier.getProducts().add(pUpdate);
			}
		});
		return pUpdate;
	}

	@Transactional
	private ProductEntity updateCategories(ProductEntity p, ProductEntity pUpdate) {
	    // Buscar as novas categorias associadas ao produto
	    Set<CategoryEntity> newCategories = p.getCategories().stream()
	            .map(cat -> catRepository.findById(cat.getId()).orElseThrow(
	                    () -> new EntityNotFoundException("Categoria não encontrada: " + cat.getDescription())))
	            .collect(Collectors.toSet());

	    // Identificar as categorias removidas
	    Set<CategoryEntity> oldCategories = new HashSet<>(pUpdate.getCategories());
	    oldCategories.removeAll(newCategories);

	    // Atualizar as categorias no produto
	    pUpdate.getCategories().clear();
	    pUpdate.getCategories().addAll(newCategories);

	    // Remover o produto das categorias que foram removidas
	    oldCategories.forEach(category -> category.getProducts().remove(pUpdate));

	    // Adicionar o produto às novas categorias (se necessário)
	    newCategories.forEach(category -> {
	        if (!category.getProducts().contains(pUpdate)) {
	            category.getProducts().add(pUpdate);
	        }
	    });

	    return pUpdate;
	}

	

}
