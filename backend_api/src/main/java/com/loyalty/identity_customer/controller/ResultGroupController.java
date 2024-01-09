package com.loyalty.identity_customer.controller;


import com.loyalty.identity_customer.request.ResultGroupRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.ResultGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ResultGroup")
public class ResultGroupController {

    @Autowired
    private ResultGroupService resultGroupService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> addResultGroup(@RequestBody ResultGroupRequest resultGroupRequest)
    {
        return resultGroupService.addResultGroup(resultGroupRequest);
    }

    @GetMapping("ByResultId/{resultId}")
    public ResponseEntity<ApiResponse<Object>> getListResultGroupByResultId(@PathVariable("resultId") Long resultId)
    {
        return resultGroupService.getResultGroupByResultId(resultId);
    }
}
