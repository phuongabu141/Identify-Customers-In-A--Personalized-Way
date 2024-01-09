package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.response.CustomerAttributeResponse;
import com.loyalty.identity_customer.service.CustomerAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer_attribute")
public class CustomerAttributeController {

    @Autowired
    private CustomerAttributeService customerAttributeService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getListCustomerAttribute()
    {
        return customerAttributeService.getListCustomerAttribute();
    }

    @GetMapping("/allCustomerAttribute")
    public ResponseEntity<ApiResponse<Object>> getAllCustomerAttribute()
    {
        return customerAttributeService.getAllCustomerAttribute();
    }

    @GetMapping("/ByCustomerId/{customerId}")
    public ResponseEntity<List<CustomerAttributeResponse>> getCustomerAttributeByCustomerId(@PathVariable("customerId") Long customerId)
    {
        return customerAttributeService.getCustomerAttributeByCustomerId(customerId);
    }
}
