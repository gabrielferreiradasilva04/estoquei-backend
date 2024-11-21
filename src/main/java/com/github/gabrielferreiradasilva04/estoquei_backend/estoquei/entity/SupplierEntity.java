package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_supplier")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SupplierEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@EqualsAndHashCode.Include
	private UUID id;
	@Column(nullable = false, length = 200)
	private String name;
	@Column(nullable = false)
	private String document;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String phoneNumber;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String siteUrl;
	@Column(nullable = false)
	@CreatedDate
	private LocalDate registrationDate;
	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updateDate;
	@Column(nullable = false, length = 400)
	private String observation;
	private Boolean active;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "suppliers", fetch = FetchType.LAZY)
	private Set<StockEntity> stocks = new HashSet<StockEntity>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "suppliers", fetch = FetchType.LAZY)
	private Set<ProductEntity> products = new HashSet<ProductEntity>();
	


}
