package com.loyalty.identity_customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Entity
@Table(name = "label_edge")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelEdgeModel {

    @EmbeddedId
    private LabelEdgeId labelEdgeId;

    @Column(name = "created_time")
    private Date created_time;

    @Column(name = "created_by")
    private Long created_by;

    @Column(name = "is_delete")
    private boolean is_delete;
}
