package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.CustomerLabelEventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerLabelEventRepository extends JpaRepository<CustomerLabelEventModel, Long> {

    @Query(value = "SELECT * FROM select_last_customer_label()", nativeQuery = true)
    List<Map<String, Object>> getAllLastCustomerLabel();

    @Query(value = "SELECT * FROM select_last_customer_label(CAST(:customerId AS integer))", nativeQuery = true)
    List<Map<String, Object>> getLastCustomerLabelByCustomerId(@Param("customerId") Long customerId);
}
