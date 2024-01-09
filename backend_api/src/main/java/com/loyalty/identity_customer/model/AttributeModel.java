package com.loyalty.identity_customer.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attribute")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attribute_id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "attribute_type")
	private String attribute_type;
	
	@Column(name = "created_time")
	private Date created_time;
	
	@Column(name = "created_by")
	private Long created_by;
	
	@Column(name = "is_delete")
	private boolean is_delete;

	@Column(name = "start_date")
	private java.sql.Date start_date;

	@Column(name = "end_date")
	private java.sql.Date end_date;

}
