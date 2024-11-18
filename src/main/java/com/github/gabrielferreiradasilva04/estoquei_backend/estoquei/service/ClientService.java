package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.ClientMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.ClientEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.ResponseClientDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.ClientRepository;

@Service
public class ClientService {
	private final ClientRepository clientRepository;
	private final ClientMapper mapper;
	
	public ClientService(ClientRepository clientRepository, ClientMapper mapper) {
		this.clientRepository = clientRepository;
		this.mapper = mapper;
	}
	
	public List<ResponseClientDto> findAll(){
		List<ClientEntity> listEntity = this.clientRepository.findAll();
		List<ResponseClientDto> listDto = listEntity.stream()
				.map(entity -> this.mapper.toResponseDto(entity))
				.collect(Collectors.toList());
		return listDto;
	}
	
	
	
}
