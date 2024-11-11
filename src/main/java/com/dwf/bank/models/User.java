package com.dwf.bank.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

	@Id
	@Column
	private UUID id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column
	private UserRole role;
	
	@Column
	private String status;
	
	@Column
	private UUID createdBy;
	
}
