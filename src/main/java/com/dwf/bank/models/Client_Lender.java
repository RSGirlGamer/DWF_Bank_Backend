package com.dwf.bank.models;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "client_lender")
public class Client_Lender {

	@Id
	@Column
	private UUID id;
	
	@Column
	private String name;
	
	@Column
	private String lastname;
	
	@Column
	private Date birthday;
	
	@Column
	private String dui;
	
	@Column
	private String address;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	@Column
	private String work_place;
	
	@Column
	private String work_start;
	
	@Column
	private String occupation;
	
	@Column
	private String work_email;
	
	@Column
	private String work_phone;
	
	@Column
	private Double salary;
	
	@Column
	private Integer credit_limit;
	
	@Enumerated(EnumType.STRING)
	@Column
	private UserRole role;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private User user;
	
	@OneToMany(mappedBy = "client")
    private Set<Account> accounts;
	
}
