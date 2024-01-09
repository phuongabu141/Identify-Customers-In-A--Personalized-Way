package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.LabelEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/label-edge")
public class LabelEdgeController {

    @Autowired
    private LabelEdgeService labelEdgeService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllLabelEdge()
    {
        return labelEdgeService.getAllLabelEdge();
    }
}
