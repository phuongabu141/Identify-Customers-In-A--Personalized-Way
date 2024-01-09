package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatorResponse {
    private Long operator_id;

    private String notation;
}
