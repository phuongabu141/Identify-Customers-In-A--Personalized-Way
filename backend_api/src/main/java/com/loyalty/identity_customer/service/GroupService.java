package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.GroupRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;


public interface GroupService {
	
	public ResponseEntity<ApiResponse<Object>> getGroupById(Long idLogic);
	
	public ResponseEntity<ApiResponse<Object>> getAllGroup();

	public ResponseEntity<ApiResponse<Object>> getListGroupByIdLabel(Long idLabel);

	public ResponseEntity<ApiResponse<Object>> getListGroupByLabelId(Long idLabel);

	public ResponseEntity<ApiResponse<Object>> getListGroupWithConditionNotNull();

	public ResponseEntity<ApiResponse<Object>> addGroup(GroupRequest groupRequest);

//	public ResponseEntity<String> checkCondition();

}
