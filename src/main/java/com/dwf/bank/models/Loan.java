package com.dwf.bank.models;

import java.util.Date;
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
@Table(name = "loan")
public class Loan {

	@Id
	@Column
	private UUID id;
	
	@ManyToOne
	@JoinColumn
	private Account account;
	
	/*@ManyToOne
	@JoinColumn
	private Client_Lender ClientLender;*/
	
	@Column
	private Double amount;
	
	@Column
	private Integer months;
	
	@Column
	private Double amortizations;
	
	@Column
	private Double debt_insurance;
	
	@Column
	private Double life_insurance;
	
	@Column
	private Double interests;
	
	@Column
	private Double montly_payment;
	
	@Column
	private Double total_amount;
	
	@Column
	private Double disbursement_fee;
	
	@Column
	private Double cashiers_check;
	
	@Column
	private Double total;
	
	@Column
	private String status;
	
	@ManyToOne
	@JoinColumn
	private Employeer opened_by;
	
	@ManyToOne
	@JoinColumn
	private Employeer revised_by;
	
	@Column
	private Date date;
	
}
