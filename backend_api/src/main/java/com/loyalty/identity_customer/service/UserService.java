package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.LoginRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<ApiResponse<Object>> getAllUser();

    public ResponseEntity<ApiResponse<Object>> loginWithPartner(LoginRequest loginRequest);

    public ResponseEntity<ApiResponse<Object>> loginWithAdmin(LoginRequest loginRequest);
}
