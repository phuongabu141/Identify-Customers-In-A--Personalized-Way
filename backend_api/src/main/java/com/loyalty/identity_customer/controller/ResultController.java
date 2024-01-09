package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.request.ResultRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllResult()
    {
        return resultService.getAllResult();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> addResult(@RequestBody ResultRequest resultRequest)
    {
        return resultService.addResult(resultRequest);
    }
}
