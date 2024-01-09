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
@Table(name = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupModel {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long group_id;
	
	@Column(name = "head_group_id")
	private Long head_group_id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "logic_id")
	private LogicModel logicModel;
	
	@Column(name = "created_time")
	private Date created_time;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "condition_id")
	private ConditionModel conditionModel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "label_id")
	private LabelModel labelModel;
	
	@Column(name = "is_delete")
	private boolean is_delete;
}