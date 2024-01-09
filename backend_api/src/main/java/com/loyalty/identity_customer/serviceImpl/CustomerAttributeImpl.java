package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.repository.CustomerAttributeRepository;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.response.CustomerAttributeResponse;
import com.loyalty.identity_customer.service.CustomerAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAttributeImpl implements CustomerAttributeService {

    @Autowired
    private CustomerAttributeRepository customerAttributeRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getListCustomerAttribute() {
        System.out.println(customerAttributeRepository.getListCustomerAttribute().size());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách thuộc tính khách hàng kèm giá trị").data(customerAttributeRepository.getListCustomerAttribute()).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getAllCustomerAttribute() {
        System.out.println(customerAttributeRepository.getAllCustomerAttribute().size());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách giá trị tính toán các thuộc tính của khách hàng").data(customerAttributeRepository.getAllCustomerAttribute()).build());
    }

    @Override
    public ResponseEntity<List<CustomerAttributeResponse>> getCustomerAttributeByCustomerId(Long customerId) {
        return ResponseEntity.status(200).body(customerAttributeRepository.getCustomerAttributeByCustomerId(customerId));
    }
}
