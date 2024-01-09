package com.loyalty.identity_customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long customer_id;

    private String customer_name;

    private String gender;

    private Date birthday;

    private String email;

    private String phone;

    private String province;

    private String district;
}
