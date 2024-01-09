package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.request.LogicRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/logic")
public class LogicController {
	
	@Autowired
	private LogicService logicService;
	
	@PostMapping(value = "/add")
	public ResponseEntity<ApiResponse<Object>> addLogic(@RequestBody LogicRequest logicRequest)
	{
		return logicService.addLogic(logicRequest);
	}
}
