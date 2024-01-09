package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.PartnerRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PartnerRequestService {

    public ResponseEntity<ApiResponse<Object>> getAllPartnerRequest();

    public ResponseEntity<ApiResponse<Object>> addPartnerRequest(PartnerRequest partnerRequest);
}
