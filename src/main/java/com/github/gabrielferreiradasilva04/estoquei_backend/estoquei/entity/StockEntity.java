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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_stock")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StockEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@EqualsAndHashCode.Include
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
	@CreatedDate
	@Column(nullable = false)
	private LocalDate registrationDate;
	
	@LastModifiedDate
	@Column
	private LocalDateTime updateDate;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tb_stock_users",
			joinColumns = @JoinColumn(name="stock_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private Set<UserEntity> users = new HashSet<UserEntity>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tb_stock_clients",
			joinColumns = @JoinColumn(name="stock_id"),
			inverseJoinColumns = @JoinColumn(name="client_id")
			)
	private Set<ClientEntity> clients = new HashSet<ClientEntity>();
	
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tb_stock_suppliers",
			joinColumns = @JoinColumn(name="stock_id"),
			inverseJoinColumns = @JoinColumn(name="supplier_id")
			)
	private Set<SupplierEntity> suppliers = new HashSet<SupplierEntity>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
	private Set<ProductEntity> products = new HashSet<ProductEntity>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
	private Set<CategoryEntity> categories = new HashSet<CategoryEntity>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
	private Set<DepositEntity> deposits = new HashSet<DepositEntity>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
	private Set<LocationEntity> locations = new HashSet<LocationEntity>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
	private Set<TransactionEntity> transactions = new HashSet<TransactionEntity>();
	
	@Override
	public String toString() {
		return "StockEntity [id=" + id + ", title=" + title + ", description=" + description + ", address=" + address
				+ ", contact=" + contact + ", totalCapacity=" + totalCapacity + ", registrationDate=" + registrationDate
				+ ", updateDate=" + updateDate + "]";
	}
	
	
}