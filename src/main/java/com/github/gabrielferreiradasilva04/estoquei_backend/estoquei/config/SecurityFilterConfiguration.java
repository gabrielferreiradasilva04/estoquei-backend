package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository.UserRepository;
import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilterConfiguration extends OncePerRequestFilter{
	
	TokenService tokenService;
	UserRepository userRepository;
	
	public static String TOKEN_JWT = "tokenJwt";
	
	public SecurityFilterConfiguration(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var token = this.recoverToken(request);
		if(token != null) {
			var email = tokenService.validateToken(token);
			UserDetails user = userRepository.findByEmail(email);
			
			if(user != null) {
				var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}	
		}
		filterChain.doFilter(request, response);
	}
	
	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");		
		}else {
			return getTokenFromCookie(request);
		}
	}
	private String getTokenFromCookie(HttpServletRequest request) {
		if(request.getCookies() != null) {
			for(Cookie cookie : request.getCookies()) {
				if(TOKEN_JWT.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	

}
