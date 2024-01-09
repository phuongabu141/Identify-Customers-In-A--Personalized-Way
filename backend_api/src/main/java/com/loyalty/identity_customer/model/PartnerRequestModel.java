package com.loyalty.identity_customer.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Entity
@Table(name = "partner_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long request_id;

    @Column(name = "created_by")
    private Long created_by;

    @Column(name = "created_time")
    private Date created_time;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "is_delete")
    private boolean is_delete;

    @Column(name = "label_name")
    private String label_name;
}
