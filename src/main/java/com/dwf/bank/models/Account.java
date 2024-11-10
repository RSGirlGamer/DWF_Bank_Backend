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
@Table(name = "account")
public class Account {
	
	@Id
	@Column
	private UUID id;
	
	@Column
	private String type;
	
	@Column
	private Double balance;
	
	@Column
	private String currency;
	
	@ManyToOne
    @JoinColumn
    private Client_Lender client;
	
}
