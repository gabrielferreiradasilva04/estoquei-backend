package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_location")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LocationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	@Column
	@CreatedDate
	private LocalDate registrationDate;
	private Boolean active;
	
	@JsonIgnore
	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
	private Set<ProductEntity> products = new HashSet<ProductEntity>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private StockEntity stock;

	
}
