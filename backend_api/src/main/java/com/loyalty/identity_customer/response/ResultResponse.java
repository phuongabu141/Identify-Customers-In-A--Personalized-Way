package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {

    private Long result_id;

    private CustomerResponse customerResponse;

    private LabelResponse labelResponse;

    private boolean logic_value;

    private Date time;
}
