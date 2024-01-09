package com.loyalty.identity_customer.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultGroupRequest {

    private Long result_id;

    private Long group_id;

    private boolean is_true;

    private String customer_value;
}
