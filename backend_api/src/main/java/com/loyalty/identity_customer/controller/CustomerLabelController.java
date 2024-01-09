package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.request.CustomerLabelRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.CustomerLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/CustomerLabel")
public class CustomerLabelController {
    @Autowired
    private CustomerLabelService customerLabelService;

    @GetMapping(value = "/labelId/{labelId}")
    public ResponseEntity<ApiResponse<Object>> getListCustomerByLabelId(@PathVariable("labelId") Long labelId)
    {
        return customerLabelService.getListCustomerByLabelId(labelId);
    }

    @PostMapping("/ByLabelId")
    public ResponseEntity<ApiResponse<Object>> listCustomerByLabelId(@RequestBody List<Long> listId)
    {
        return customerLabelService.listCustomerByLabelId(listId);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> addCustomerLabel(@RequestBody CustomerLabelRequest customerLabelRequest)
    {
        return customerLabelService.addCustomerLabel(customerLabelRequest);
    }
}
