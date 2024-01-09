package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastCustomerLabelResponse {
    private Long customer_label_id;

    private Long customer_id;

    private Date created_time;

    private Long label_id;

    private Long last_event_id;

    private boolean is_delete;

    private Long event_type_id;
}
