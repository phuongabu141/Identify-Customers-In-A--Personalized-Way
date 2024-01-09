package com.loyalty.identity_customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLabelEventRequest {

    private Long event_type_id;

    private Long label_id;

    private Long customer_id;

    private Long result_id;
}
