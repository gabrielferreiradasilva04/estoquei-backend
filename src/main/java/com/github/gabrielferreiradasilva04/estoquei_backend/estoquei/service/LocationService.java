package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.LocationMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.LocationEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseLocationDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.LocationRepository;

@Service
public class LocationService {
	private final LocationRepository locationRepository;
	private final LocationMapper mapper;
	
	public LocationService(LocationRepository locationRepository, LocationMapper mapper) {
		this.locationRepository = locationRepository;
		this.mapper = mapper;
	}
	
	public List<ResponseLocationDto> findAll(){
		List<LocationEntity> listEntity = this.locationRepository.findAll();
		List<ResponseLocationDto> listDto = listEntity.stream()
				.map(entity -> this.mapper.toDto(entity)).collect(Collectors.toList());
		return listDto;
	}
}
