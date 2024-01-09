package com.loyalty.identity_customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "result_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultGroupModel {

    @EmbeddedId
    private ResultGroupId resultGroupId;

    @Column(name = "logic_value")
    private boolean logic_value;

    @Column(name = "customer_value")
    private String customer_value;
}
