package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.repository.LabelEdgeRepository;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.LabelEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LabelEdgeServiceImpl implements LabelEdgeService {

    @Autowired
    private LabelEdgeRepository labelEdgeRepository;


    @Override
    public ResponseEntity<ApiResponse<Object>> getAllLabelEdge() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("All label edge").data(labelEdgeRepository.getAllLabelEdge()).build());
    }
}
