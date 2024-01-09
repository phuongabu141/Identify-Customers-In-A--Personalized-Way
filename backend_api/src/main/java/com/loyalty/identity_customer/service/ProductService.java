package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.ProductRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    public ResponseEntity<ApiResponse<Object>> getAllProduct();

    public ResponseEntity<ApiResponse<Object>> addProduct(ProductRequest productRequest);
}
