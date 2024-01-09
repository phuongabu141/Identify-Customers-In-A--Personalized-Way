package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllAttribute()
    {
        return attributeService.getAllAttribute();
    }
}
