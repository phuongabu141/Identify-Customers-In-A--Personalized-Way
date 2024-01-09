package com.loyalty.identity_customer.controller;


import com.loyalty.identity_customer.request.PartnerRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.PartnerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/PartnerRequest")
public class PartnerRequestController {

    @Autowired
    private PartnerRequestService partnerRequestService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllPartnerRequest()
    {
        return partnerRequestService.getAllPartnerRequest();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> addPartnerRequest(@RequestBody PartnerRequest partnerRequest)
    {
        return partnerRequestService.addPartnerRequest(partnerRequest);
    }
}
