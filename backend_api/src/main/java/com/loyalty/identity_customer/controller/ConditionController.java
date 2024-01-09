package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.request.ConditionRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/condition")
public class ConditionController {

    @Autowired
    private ConditionService conditionService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getConditionById(@PathVariable("id") Long idCondition)
    {
        return conditionService.getConditionById(idCondition);
    }

//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse<Object>> addCondition(@RequestBody ConditionRequest conditionRequest)
//    {
//        return conditionService.addCondition(conditionRequest);
//    }
}
