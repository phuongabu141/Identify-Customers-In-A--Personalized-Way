package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.ResultCustomerLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/ResultCustomerLabel")
public class ResultCustomerLabelController {

    @Autowired
    private ResultCustomerLabelService resultCustomerLabelService;

    @GetMapping()
    public ResponseEntity<ApiResponse<Object>> getResultCustomerLabel(@RequestParam("customerId") Long customerId,
                                                                      @RequestParam("labelId") Long labelId)
    {
        return resultCustomerLabelService.getResultCustomerLabel(customerId, labelId);
    }
}
