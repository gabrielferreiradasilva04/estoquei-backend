package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UserEntity;

@Service
public class TokenService {

	@Value("$app.config.secret")
	private String key;

	/***
	 * metodo que cria o token do usuário no momento em que ele se autentica
	 * @param user
	 * @return
	 */
	public String generateToken(UserEntity user) {
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(key);
			String token = JWT.create()
					.withIssuer("estoquei")
					.withSubject(user.getEmail())
					.sign(algorithm);
			
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar o token");
			
		}
	}
	
	/***
	 * valida o token e retorna o usuário
	 * @param token
	 * @return
	 */
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(key);

			return JWT.require(algorithm)
					.withIssuer("estoquei")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			return "";
		}
	}
}
