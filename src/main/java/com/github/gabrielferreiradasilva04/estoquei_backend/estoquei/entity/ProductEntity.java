package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@EqualsAndHashCode.Include
	private UUID id;
	@Column(nullable = false, length = 200)
	private String code;
	@Column(nullable = false, length = 400)
	private String description;
	@Column(nullable = false, scale = 2, precision = 18)
	private BigDecimal priceCost;
	@Column(nullable = false, scale = 2, precision = 18)
	private BigDecimal salePrice;
	@Column
	private Double quantityStock;
	@Column
	private Double minimumStock;
	@Column
	private Boolean active;
	@CreatedDate
	@Column(nullable = false)
	private LocalDate registrationDate;
	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updateDate;
	
	
	@ManyToOne(
			fetch = FetchType.LAZY
			)
	@JoinColumn(name = "unit_measure_id")
	private UnitMeasureEntity unitMeasure;
	
	@JsonIgnore
	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
	private List<ProductPhotoEntity> productPhotos = new ArrayList<ProductPhotoEntity>();
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="stock_id")
	private StockEntity stock;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="tb_product_suppliers",
			joinColumns = @JoinColumn(name="product_id"),
			inverseJoinColumns = @JoinColumn(name="supplier_id")
			)
	private Set<SupplierEntity> suppliers = new HashSet<SupplierEntity>();
	
	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	private Set<CategoryEntity> categories = new HashSet<CategoryEntity>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id")
	private LocationEntity location;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private Set<ProductDepositEntity> productDepositEntities = new HashSet<ProductDepositEntity>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private BrandEntity brand;
	
	
	
	
	

}
