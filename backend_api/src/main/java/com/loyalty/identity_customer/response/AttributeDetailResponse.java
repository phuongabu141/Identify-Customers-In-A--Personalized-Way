package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDetailResponse {

    private Long attribute_id;

    private String attribute_name;

    private Date startDate;

    private Date endDate;
}
