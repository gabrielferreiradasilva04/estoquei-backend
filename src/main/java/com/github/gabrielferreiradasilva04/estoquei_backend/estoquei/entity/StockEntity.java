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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_stock")
public class StockEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false, length = 200, unique = true)
	private String title;
	@Column(nullable = false, length = 400)
	private String description;
	@Column
	private String address;
	@Column
	private String contact;
	@Column
	private Double totalCapacity;
	@Column(nullable = false)
	private LocalDate registrationDate;
}
