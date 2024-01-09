package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.ResultGroupRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ResultGroupService {

    public ResponseEntity<ApiResponse<Object>> addResultGroup(ResultGroupRequest resultGroupRequest);

    public ResponseEntity<ApiResponse<Object>> getResultGroupByResultId(Long resultId);
}
