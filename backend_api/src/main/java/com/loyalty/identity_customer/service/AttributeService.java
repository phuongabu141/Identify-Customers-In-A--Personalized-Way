package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AttributeService {

    public ResponseEntity<ApiResponse<Object>> getAllAttribute();
}
