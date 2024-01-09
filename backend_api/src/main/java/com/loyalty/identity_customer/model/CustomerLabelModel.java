package com.loyalty.identity_customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Entity
@Table(name = "customer_label")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLabelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_label_id;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerModel customerModel;

    @Column(name = "created_time")
    private Date created_time;

    @Column(name = "created_by")
    private Long created_by;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "label_id")
    private LabelModel labelModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "last_event_id")
    private CustomerLabelEventModel customerLabelEventModel;

    @Column(name = "is_delete")
    private boolean is_delete;
}
