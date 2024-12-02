package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Entity
@Table(name="tb_movement")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MovementEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime date;
	@Column
	private Double quantity;
	@Column(nullable = false, length = 400)
	private String observation;
	@Column
	private BigDecimal total;
	@Column
	private String invoice;
	
	@ManyToOne
	@JoinColumn(name = "product_deposit_id")
	private ProductDepositEntity productDeposit;
	
	@ManyToOne
	@JoinColumn(name = "movement_type_id")
	private MovementTypeEntity movementType;
	
}
