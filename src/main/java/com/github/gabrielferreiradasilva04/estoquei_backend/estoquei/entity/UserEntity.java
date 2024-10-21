package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class UserEntity extends PeopleEntity{
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
	@Column(nullable = false)
	private LocalDate registrationDate;
	@Column(nullable = false)
	private LocalDate updateDate;
	@Column(name = "active", nullable = false)
	private Boolean active;
	@Column(nullable = false, length = 200)
	private String password;
	@Column(nullable = false, length = 200, unique = true)
	private String email;
	

}
