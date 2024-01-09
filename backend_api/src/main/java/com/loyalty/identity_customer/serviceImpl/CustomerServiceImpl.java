package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.repository.CustomerRepository;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ApiResponse<Object> getAllCustomer()
    {
        return ApiResponse.builder().data(customerRepository.getAllCustomer()).build();
    }

    @Override
    public ApiResponse<Object> getCustomerById(Long idCustomer)
    {
        return ApiResponse.builder().data(customerRepository.findById(idCustomer)).message("Lấy khách hàng theo id khách hàng").build();
    }
}
