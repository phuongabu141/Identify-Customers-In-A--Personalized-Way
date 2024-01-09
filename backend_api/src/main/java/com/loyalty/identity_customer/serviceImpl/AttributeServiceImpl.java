package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.repository.AttributeRepository;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getAllAttribute(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("All attribute").data(attributeRepository.getAllAttribute()).build());
    }
}
