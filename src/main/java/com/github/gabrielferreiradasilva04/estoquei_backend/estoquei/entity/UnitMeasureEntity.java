package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tb_unit_measure")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UnitMeasureEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String code;
	@CreatedDate
	@Column(nullable = false)
	private LocalDate registerDate;
	@Column(nullable = false)
	private LocalDateTime updateDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "unitMeasure", fetch = FetchType.LAZY)
	private List<ProductEntity> products = new ArrayList<ProductEntity>();

}
