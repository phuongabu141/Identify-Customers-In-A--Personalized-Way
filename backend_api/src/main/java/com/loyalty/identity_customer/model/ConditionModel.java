package com.loyalty.identity_customer.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "condition")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long condition_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_id")
	private AttributeModel attributeModel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "operator_id")
	private OperatorModel operatorModel;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "created_time")
	private Date created_time;
	
	@Column(name = "created_by")
	private Long created_by;
	
	@Column(name = "is_delete")
	private boolean is_delete;
}
