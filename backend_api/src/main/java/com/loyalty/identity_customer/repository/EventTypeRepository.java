package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.EventTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTypeRepository extends JpaRepository<EventTypeModel, Long> {
}
