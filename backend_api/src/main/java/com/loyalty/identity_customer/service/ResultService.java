package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.ResultRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ResultService {

    public ResponseEntity<ApiResponse<Object>> getAllResult();

    public ResponseEntity<ApiResponse<Object>> addResult(ResultRequest resultRequest);
}
