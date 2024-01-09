package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.CustomerLabelEventRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerLabelEventService {
    public ResponseEntity<ApiResponse<Object>> getAllLastCustomerLabel();

    public ResponseEntity<ApiResponse<Object>> getLastCustomerLabelByCustomerId(Long customerId);

    public ResponseEntity<ApiResponse<Object>> addCustomerLabelEvent(CustomerLabelEventRequest customerLabelEventRequest);
}
