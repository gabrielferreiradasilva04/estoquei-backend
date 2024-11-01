package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="tb_estimate")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EstimateEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false, length = 200)
	private String title;
	@Column(nullable = false, length = 400)
	private String description;
	@CreatedDate
	@Column(nullable = false)
	private LocalDate date;
	@Column(precision = 18, scale=2)
	private BigDecimal total;
	@Column
	private LocalDate expirationDate;
	@Column(nullable = false, length = 400)
	private String observation;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private EstimateStatusEntity status;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private ClientEntity client;

}
