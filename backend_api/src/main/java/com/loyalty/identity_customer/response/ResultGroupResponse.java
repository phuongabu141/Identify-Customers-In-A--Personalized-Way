package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultGroupResponse {
    private Long result_id;

    private GroupResponse groupResponse;

    private boolean logic_value;

    private String customer_value;
}
