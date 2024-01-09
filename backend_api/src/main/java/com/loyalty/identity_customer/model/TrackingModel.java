package com.loyalty.identity_customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tracking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tracking_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "condition_id")
    private ConditionModel conditionModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_label_id")
    private CustomerLabelModel customerLabelModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_type_id")
    private EventTypeModel eventTypeModel;

    @Column(name = "value")
    private String value;

    @Column(name = "created_by")
    private Long created_by;

    @Column(name = "created_time")
    private Date created_time;

    @Column(name = "description")
    private String description;

    @Column(name = "is_delete")
    private boolean is_delete;
}
