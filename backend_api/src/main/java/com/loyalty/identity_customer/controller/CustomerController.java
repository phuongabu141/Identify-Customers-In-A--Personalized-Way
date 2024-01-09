package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/all")
    public ApiResponse<Object> getAllCustomer()
    {
        return customerService.getAllCustomer();
    }
    
    @GetMapping(value = "/{id}")
    public ApiResponse<Object> getCustomerByCustomer(@PathVariable("id") Long idCustomer)
    {
    	return customerService.getCustomerById(idCustomer);
    }

}
