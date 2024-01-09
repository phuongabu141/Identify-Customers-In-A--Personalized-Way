package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.request.CustomerLabelEventRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.CustomerLabelEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/CustomerLabelEvent")
public class CustomerLabelEventController {

    @Autowired
    private CustomerLabelEventService customerLabelEventService;

    @GetMapping("/lastEvent")
    public ResponseEntity<ApiResponse<Object>> getAllLastCustomerLabel()
    {
        return customerLabelEventService.getAllLastCustomerLabel();
    }

    @GetMapping("/lastEventOfCustomerId/{customerId}")
    public ResponseEntity<ApiResponse<Object>> getLastCustomerLabelByCustomerId(@PathVariable("customerId") Long customerId)
    {
        return customerLabelEventService.getLastCustomerLabelByCustomerId(customerId);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> addCustomerLabelEvent(@RequestBody CustomerLabelEventRequest customerLabelEventRequest)
    {
        return customerLabelEventService.addCustomerLabelEvent(customerLabelEventRequest);
    }
}
