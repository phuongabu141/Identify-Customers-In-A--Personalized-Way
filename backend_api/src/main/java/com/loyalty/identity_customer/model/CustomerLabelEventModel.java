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
@Table(name = "customer_label_event")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLabelEventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_label_event_id;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time")
    private Date created_time;

    @Column(name = "created_by")
    private Long created_by;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "label_id")
    private LabelModel labelModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "result_id")
    private ResultModel resultModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_type_id")
    private EventTypeModel eventTypeModel;

    @Column(name = "is_delete")
    private boolean is_delete;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerModel customerModel;
}
