package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller.mapper.UpdateUserMapper;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.UpdateUserDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final UpdateUserMapper updateMapper;

	public UserController(UserService service, UpdateUserMapper updateMapper) {
		this.userService = service;
		this.updateMapper = updateMapper;
	}

	@PutMapping
	public ResponseEntity<Object> updateUser(@RequestBody @Valid UpdateUserDto dto) {
		UserEntity authUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity userEntity = updateMapper.toEntity(dto);
		this.userService.update(authUser.getId(), userEntity);

		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/stocks/{userId}")
	public ResponseEntity<Object> findUserStocks(@PathVariable String userId){
		try {
			var uuid = UUID.fromString(userId);
			if(uuid != null){
				return ResponseEntity.ok().body(this.userService.findUserStocks(uuid));
			}
			return ResponseEntity.notFound().build();
		} catch ( IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
