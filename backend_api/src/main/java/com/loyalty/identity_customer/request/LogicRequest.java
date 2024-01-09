package com.loyalty.identity_customer.request;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LogicRequest {
	private String logic_name;
	
	private String description;
	
	private String notation;
	
	private Date created_date;
	
	private Long created_by;
}
