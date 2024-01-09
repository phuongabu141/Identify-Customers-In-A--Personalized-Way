package com.loyalty.identity_customer.service;

import com.loyalty.identity_customer.response.ApiResponse;


public interface CustomerService {

    public ApiResponse<Object> getAllCustomer();
    
    public ApiResponse<Object> getCustomerById(Long idCustomer);
}
