package com.loyalty.identity_customer.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GroupRequest {
    private Long group_id;

    private Long head_group_id;

    private Long logic_id;

    private Long label_id;

    private ConditionRequest conditionRequest;
}
