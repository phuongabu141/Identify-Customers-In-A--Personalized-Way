package com.loyalty.identity_customer.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class LabelRequest {

    private String label_name;

    private String description;

    private Long head_label_id;
}
