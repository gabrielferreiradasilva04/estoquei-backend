package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ProductEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseProductDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.SaveProductDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.UpdateProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "productPhotos", ignore = true)
	@Mapping(target = "quantityStock", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "suppliers", ignore = true)
	@Mapping(target = "productDepositEntities", ignore = true)
	ProductEntity saveDtoToEntity(SaveProductDto dto); 
	
	@Mapping(target = "productPhotos", ignore = true)
	@Mapping(target = "quantityStock", ignore = true)
	@Mapping(target = "registrationDate", ignore = true)
	@Mapping(target = "updateDate", ignore = true)
	@Mapping(target = "productDepositEntities", ignore = true)
	ProductEntity updateDtoToEntity(UpdateProductDto dto);  
	
	SaveProductDto entityToSaveDto(ProductEntity entity);
	
	ResponseProductDto entityToResponseDto(ProductEntity entity);

}
