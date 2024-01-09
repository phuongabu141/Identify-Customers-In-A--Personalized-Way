package com.loyalty.identity_customer.response;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LabelDetailResponse {

    private Long label_id;

    private String label_name;

    private Long label_head_id;

    private Date created_time;

}
