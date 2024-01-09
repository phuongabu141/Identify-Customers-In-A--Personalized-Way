package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.PartnerRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRequestRepository extends JpaRepository<PartnerRequestModel, Long> {

}
