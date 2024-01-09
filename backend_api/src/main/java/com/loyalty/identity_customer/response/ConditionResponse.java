package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionResponse {

    private Long condition_id;

    private AttributeResponse attributeResponse;

    private OperatorResponse operatorResponse;

    private String value;
}
