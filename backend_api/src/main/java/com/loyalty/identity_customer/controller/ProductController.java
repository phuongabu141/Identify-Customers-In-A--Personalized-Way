package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.request.ProductRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all")
    public ResponseEntity<ApiResponse<Object>> getAllProduct()
    {
        return productService.getAllProduct();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse<Object>> addProduct(@RequestBody ProductRequest productRequest){
        return productService.addProduct(productRequest);
    }
}
