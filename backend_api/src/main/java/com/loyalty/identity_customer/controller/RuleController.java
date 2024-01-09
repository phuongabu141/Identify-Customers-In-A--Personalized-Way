package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.service.AttributeService;
import com.loyalty.identity_customer.serviceImpl.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private AttributeService attributeService;

    @GetMapping("/check")
    public ResponseEntity<String> checkRule(@RequestParam("labelId") Long labelId)
    {
        return ruleService.ruleForAllCustomer(labelId);
    }
//    public ResponseEntity<ApiResponse<Object>> getAllAttribute()
//    {
//        return attributeService.getAllAttribute();
//    }
}
