package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.request.LoginRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/loginWithPartner")
    public ResponseEntity<ApiResponse<Object>> loginWithPartner(@RequestBody LoginRequest loginRequest)
    {
        return userService.loginWithPartner(loginRequest);
    }

    @PostMapping("/loginWithAdmin")
    public ResponseEntity<ApiResponse<Object>> loginWithAdmin(@RequestBody LoginRequest loginRequest)
    {
        return userService.loginWithAdmin(loginRequest);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ApiResponse<Object>> getAllUser()
    {
        return userService.getAllUser();
    }
}
