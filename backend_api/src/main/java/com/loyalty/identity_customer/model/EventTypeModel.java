package com.loyalty.identity_customer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kie.api.definition.rule.All;

import java.util.Date;

@Entity
@Table(name = "event_type")
@Data
@NoArgsConstructor
@All
public class EventTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_type_id;

    @Column(name = "event_name")
    private String event_name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_time")
    private Date created_time;

    @Column(name = "created_by")
    private Long created_by;

    @Column(name = "is_delete")
    private boolean is_delete;
}
