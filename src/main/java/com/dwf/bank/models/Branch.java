package com.dwf.bank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "branch")
public class Branch {

	@Id
	@GeneratedValue
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@OneToOne
	@JoinColumn
	private Employeer manager;
}
