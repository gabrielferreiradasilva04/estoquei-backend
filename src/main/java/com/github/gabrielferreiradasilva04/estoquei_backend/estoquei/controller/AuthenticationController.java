package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.config.SecurityFilterConfiguration;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.AuthenticationDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.LoginResponseDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos.UserEntityDto;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.TokenService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
	
	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private TokenService tokenService;
	
	public AuthenticationController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.userRepository = repository;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto auth, HttpServletResponse response){
		//processo de autenticação
		var userNamePassword = new UsernamePasswordAuthenticationToken(auth.email(), auth.password());
		var authenticate = this.authenticationManager.authenticate(userNamePassword);
		var token = tokenService.generateToken((UserEntity) authenticate.getPrincipal());
		
		//armazenar o token em um cookie no cliente
		Cookie jwtCookie = new Cookie(SecurityFilterConfiguration.TOKEN_JWT, token);
		jwtCookie.setHttpOnly(true); //estou protegendo o meu token contra scripts javascript
		jwtCookie.setPath("/"); //especificando para quais endpoints o token vai ser mandado
		jwtCookie.setMaxAge(60*60);//define a expiração do token para 1 dia inteiro
		jwtCookie.setSecure(false);
		 //adicionando o atributo sameSite no header de retorno do cookie para que ele seja salvo em qualquer requisição
		String sameSite = "None";
		response.addHeader("Set-Cookie", String.format("%s=%s; HttpOnly; Path=%s; Max-Age=%d; SameSite=%s; Secure", 
				jwtCookie.getName(), jwtCookie.getValue(), jwtCookie.getPath(), jwtCookie.getMaxAge(), sameSite));
		response.addCookie(jwtCookie);
		//retornar resposta ao cliente.
		return ResponseEntity.ok(new LoginResponseDto(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid UserEntityDto userDto){
		UserEntity user = userDto.toUserEntity(); 
		if(this.userRepository.findByEmail(user.getEmail()) != null) {
			return ResponseEntity.badRequest().build();
		}else {
			String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encryptedPassword);
			userRepository.save(user);
			return ResponseEntity.ok().build();
		}
	}
}
