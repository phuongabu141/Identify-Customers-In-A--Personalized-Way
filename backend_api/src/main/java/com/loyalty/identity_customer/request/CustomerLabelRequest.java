package com.loyalty.identity_customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLabelRequest {

    private Long customer_id;

    private Long label_id;

    private Long last_event_id;
}
