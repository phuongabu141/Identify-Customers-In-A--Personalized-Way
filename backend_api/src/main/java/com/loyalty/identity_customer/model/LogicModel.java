package com.loyalty.identity_customer.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "logic")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class LogicModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long logic_id;

	@Column(name = "logic_name")
	private String logic_name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "notation")
	private String notation;
	
	@Column(name = "created_time")
	private Date created_time;
	
	@Column(name = "created_by")
	private Long created_by;
}
