package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.LogicRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;


public interface LogicService {

	public ResponseEntity<ApiResponse<Object>> addLogic(LogicRequest logicRequest);
}
