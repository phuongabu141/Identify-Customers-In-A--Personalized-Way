package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAttributeResponse {

    private Long customer_id;

    private Long attribute_id;

    private String value;
}
