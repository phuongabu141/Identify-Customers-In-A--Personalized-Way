package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultCustomerLabelResponse {
    private Long customer_id;

    private Long label_id;

    private String label_name;

    private Long condition_id;

    private Long attribute_id;

    private String attribute_name;

    private Long operator_id;

    private String ope_notation;

    private String value;

    private Long group_id;

    private Long head_group_id;

    private Long logic_id;

    private String log_notation;

    private String customer_value;

    private boolean result_bool;
}
