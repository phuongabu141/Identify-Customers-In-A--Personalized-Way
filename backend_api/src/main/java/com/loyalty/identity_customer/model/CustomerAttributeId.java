package com.loyalty.identity_customer.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CustomerAttributeId implements Serializable {
    private Long customerId;
    private Long attributeId;

    // Constructors, getters, setters, equals, and hashCode methods
}