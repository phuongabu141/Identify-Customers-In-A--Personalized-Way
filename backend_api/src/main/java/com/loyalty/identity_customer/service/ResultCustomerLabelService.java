package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;

public interface ResultCustomerLabelService {
    public ResponseEntity<ApiResponse<Object>> getResultCustomerLabel(Long customerId, Long labelId);
}
