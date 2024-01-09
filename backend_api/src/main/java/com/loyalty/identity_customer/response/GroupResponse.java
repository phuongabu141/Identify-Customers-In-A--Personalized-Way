package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    private Long group_id;

    private Long head_group_id;

    private LogicResponse logicResponse;

    private ConditionResponse conditionResponse;

    private LabelResponse labelResponse;
}
