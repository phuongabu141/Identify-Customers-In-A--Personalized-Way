package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.OperatorModel;
import com.loyalty.identity_customer.response.OperatorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<OperatorModel, Long> {

    @Query("SELECT om FROM OperatorModel om WHERE om.operator_id = :id")
    OperatorModel findOperatorById(@Param("id") Long id);
}
