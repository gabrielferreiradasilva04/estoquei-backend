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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_stock")
@EntityListeners(AuditingEntityListener.class)
public class StockEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@CreatedDate
	@Column(nullable = false)
	private LocalDate registrationDate;
	
	@LastModifiedDate
	@Column
	private LocalDateTime updateDate;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "stocks")
	private Set<UserEntity> users = new HashSet<UserEntity>();

	@Override
	public String toString() {
		return "StockEntity [id=" + id + ", title=" + title + ", description=" + description + ", address=" + address
				+ ", contact=" + contact + ", totalCapacity=" + totalCapacity + ", registrationDate=" + registrationDate
				+ ", updateDate=" + updateDate + "]";
	}
	
	
}