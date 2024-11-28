package com.dwf.bank.models;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "movements")
public class Movements {

	@Id
	@Column
	private UUID id;
	
	@Column
	private String description;
	
	@Column (nullable=false)
	@Enumerated(EnumType.STRING)
	private MovementType type;
	
	@Column
	private Double amount;
	
	/*@Column
	private String is_less_or_more;*/
	
	@Column
	private Date date;
	
	@ManyToOne
	@JoinColumn
	private Account account_transmitter;
	
	@ManyToOne
	@JoinColumn(nullable=true)
	private Account account_receiver;

}
