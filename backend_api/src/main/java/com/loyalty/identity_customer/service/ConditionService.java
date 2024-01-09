package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.ConditionRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ConditionService {

    public ResponseEntity<ApiResponse<Object>> getConditionById(Long idCondition);

//    public ResponseEntity<ApiResponse<Object>> addCondition(ConditionRequest conditionRequest);
}
