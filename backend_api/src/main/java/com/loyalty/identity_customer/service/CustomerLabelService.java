package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.CustomerLabelRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerLabelService {

    public ResponseEntity<ApiResponse<Object>> getListCustomerByLabelId(Long labelId);

    public ResponseEntity<ApiResponse<Object>> listCustomerByLabelId(List<Long> listLabelId);

    public ResponseEntity<ApiResponse<Object>> addCustomerLabel(CustomerLabelRequest customerLabelRequest);

}
