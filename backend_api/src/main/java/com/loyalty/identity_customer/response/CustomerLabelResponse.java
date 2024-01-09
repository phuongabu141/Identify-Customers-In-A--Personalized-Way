package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLabelResponse {
    private String label_name;

    private CustomerResponse customerResponse;
}
