package com.loyalty.identity_customer.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductRequest {

    private String product_name;

    private Long price;

    private String brand_name;

    private String main_cate;

    private String sub_cate;

    private String product_description;

    private String unit;

    private String origin;

}
