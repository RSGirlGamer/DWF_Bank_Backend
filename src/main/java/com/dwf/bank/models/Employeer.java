package com.dwf.bank.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "employeer")
public class Employeer {

	@Id
	@Column
	private UUID id;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
	@Column
	private String role;
	
	@ManyToOne
	@JoinColumn
	private Branch branch;
	
	@Column
	private UUID added_by;
	
	@Column
	private String status;
	
	@Column
	private UUID revised_by;
	
	@Column
	private String name;
	
	@Column
	private String lastname;
	
}
