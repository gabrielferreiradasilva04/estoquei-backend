package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.config.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends PeopleEntity implements UserDetails{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false, length = 200, unique = true)
	private String nickname;
	@Column(nullable = false, length = 200)
	private String firstName;
	@Column(nullable = false, length = 200)
	private String lastName;
	@Column(nullable = false, length = 200, unique = true)
	private String phoneNumber;
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateDate;
	@Column(name = "active", nullable = false)
	private Boolean active;
	@Column(nullable = false, length = 200)
	private String password;
	@Column(nullable = false, length = 200, unique = true)
	private String email;
	@Enumerated(EnumType.STRING)
	@Column
	private UserRole userType;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.userType == UserRole.ADMINISTRADOR) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		}else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
	}
	@Override
	public String getUsername() {
		return this.email; /*retorna o atributo usado para realizar a autenticação na api*/
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true; /*especifica se a conta nunca expira*/
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true; /*especifica se a conta nunca é bloqueada*/
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true; /*as credenciais de acesso nunca expiram*/
	}
	
	@Override
	public boolean isEnabled() {
		return true; /*especifica se o usuário está ativo*/
	}
	

}
