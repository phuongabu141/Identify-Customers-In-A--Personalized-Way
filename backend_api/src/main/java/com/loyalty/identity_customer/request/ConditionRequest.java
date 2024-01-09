package com.loyalty.identity_customer.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConditionRequest {

    private Long attribute_id;

    private Long operator_id;

    private String value;
}
