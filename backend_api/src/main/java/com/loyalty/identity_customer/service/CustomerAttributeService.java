package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.response.CustomerAttributeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerAttributeService {

    public ResponseEntity<ApiResponse<Object>> getListCustomerAttribute();

    public ResponseEntity<ApiResponse<Object>> getAllCustomerAttribute();

    public ResponseEntity<List<CustomerAttributeResponse>> getCustomerAttributeByCustomerId(Long customerId);
}
