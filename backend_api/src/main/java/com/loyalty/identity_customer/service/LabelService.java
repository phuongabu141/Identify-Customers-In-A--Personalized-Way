package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.request.LabelRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface LabelService {

    public ResponseEntity<ApiResponse<Object>> getLabelMapGroup();

    // function active Label
    public ResponseEntity<ApiResponse<Object>> activeLabel(Long labelId);

    public ResponseEntity<ApiResponse<Object>> getAllLabel();

    public ResponseEntity<ApiResponse<Object>> findByName(String labelName);

    public ResponseEntity<ApiResponse<Object>> findLabelActivated();

    public ResponseEntity<ApiResponse<Object>> addLabel(LabelRequest labelRequest);

    public ResponseEntity<ApiResponse<Object>> getLabelById(Long labelId);
}
