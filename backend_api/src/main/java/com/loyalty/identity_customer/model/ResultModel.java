package com.loyalty.identity_customer.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@Entity
@Table(name = "result")
@AllArgsConstructor
@NoArgsConstructor
public class ResultModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long result_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerModel customerModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "label_id")
    private LabelModel labelModel;

    @Column(name = "logic_value")
    private boolean logic_value;

    @Column(name = "time")
    private Date time;
}
