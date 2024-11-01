package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity;

import java.io.Serializable;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ClientEntity extends PeopleEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private String phoneNumber;
	@Column(nullable = false, unique = true )
	private String email;
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateDate;
	@Column
	private Boolean active;
	@Column
	private String observation;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "clients",fetch = FetchType.LAZY)
	private Set<StockEntity> stocks = new HashSet<StockEntity>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<EstimateEntity> estimates = new ArrayList<EstimateEntity>();
	
	@Override
	public String toString() {
		return "ClientEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", registrationDate=" + registrationDate + ", updateDate="
				+ updateDate + ", active=" + active + "]";
	}
	
	

}
